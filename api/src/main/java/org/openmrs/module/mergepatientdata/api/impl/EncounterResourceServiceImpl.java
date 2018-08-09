package org.openmrs.module.mergepatientdata.api.impl;

import java.util.List;
import java.util.Set;

import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Person;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.mergepatientdata.api.EncounterResourceService;
import org.openmrs.module.mergepatientdata.api.exceptions.MissingMetadataException;
import org.openmrs.module.mergepatientdata.api.model.audit.PaginatedAuditMessage;
import org.openmrs.module.mergepatientdata.api.utils.ObjectUtils;
import org.openmrs.module.mergepatientdata.resource.Encounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncounterResourceServiceImpl implements EncounterResourceService {
	
	private final static Logger log = LoggerFactory.getLogger(EncounterResourceServiceImpl.class);
	
	public EncounterResourceServiceImpl() {
	}
	
	@Override
	public org.openmrs.Encounter saveEncounter(org.openmrs.Encounter encounter, PaginatedAuditMessage auditor) {
		if (encounter == null) {
			return null;
		}
		try {
			return Context.getEncounterService().saveEncounter(encounter);
		}
		catch (org.openmrs.api.ValidationException e) {
			log.error("Tried to merge an invalid Encounter, {}", e.getMessage());
			auditor.setHasErrors(true);
			auditor.getFailureDetails().add(
			    "Failed to Merge 'Encounter' of patient "
			            + Context.getPatientService().getPatient(encounter.getPatient().getId()).getGivenName()
			            + "' rationale: " + e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void saveEncounters(List<Encounter> list, PaginatedAuditMessage auditor) {
		
		if (list != null && list.isEmpty()) {
			return;
		}
		List<org.openmrs.Encounter> encounters = (List<org.openmrs.Encounter>) ObjectUtils
		        .getOpenmrsResourceObjectsFromMPDResourceObjects(list);
		int counter = 0;
		for (org.openmrs.Encounter encounter : encounters) {
			// We are currently not supporting visits
			encounter.setVisit(null);
			// Check whether Encounter is new
			if (encounter.getId() != null) {
				org.openmrs.Encounter enc = Context.getEncounterService().getEncounterByUuid(encounter.getUuid());
				if (enc != null) {
					
					if (encounter.getPatient().getId() == enc.getPatient().getId()) {
						// Clear the Session to make the update possible
						Context.clearSession();
						// Mean while, don't update, just continue
						continue;
					} else {
						log.warn("Encounter " + encounter.getId() + " seems to be existing but on a wrong Patient");
						// This means that this Encounter already exists but was assigned to a wrong Patient
						// TODO Implement something better to solve this
						// Lets void this Encounter found
						enc.setVoided(true);
						enc.setVoidReason("This Encounter was assigned to a wrong Patient During Transer");
						Context.getEncounterService().saveEncounter(enc);
						// Lets Do the actual update
						encounter.setId(null);
						encounter.setUuid(null);
						//Context.getEncounterService().purgeEncounter(enc);
						inspectEncounterPropertiesAndModifyIfRequired(encounter);
						
					}
				} else {
					encounter.setId(null);
					inspectEncounterPropertiesAndModifyIfRequired(encounter);
				}
			}
			org.openmrs.Encounter savedEncounter = saveEncounter(encounter, auditor);
			if (savedEncounter != null) {
				counter++;
			}
		}
		auditor.getResourceCount().put("Encounter", counter);
	}
	
	private org.openmrs.Encounter inspectEncounterPropertiesAndModifyIfRequired(org.openmrs.Encounter enc) {
		log.debug("Started inspecting properties of " + enc);
		// Update Creator
		if (enc.getCreator() != null) {
			User creator = Context.getUserService().getUserByUuid(enc.getCreator().getUuid());
			if (creator != null) {
				enc.setCreator(creator);
			} else {
				// This User isn't known
				throw new MissingMetadataException("Missing User of uuid#" + enc.getCreator().getUuid()
				        + " Make sure its present before Merging");
			}
		}
		// Update Location Resource
		Location location = enc.getLocation();
		if (location != null) {
			if (location.getUuid() != null) {
				Location existingLocation = Context.getLocationService().getLocationByUuid(location.getUuid());
				System.out.println("Return Location of uuid " + location.getUuid() + " is " + existingLocation);
				// Check if its already existing
				if (existingLocation != null) {
					enc.setLocation(existingLocation);
				} else {
					// Location is not known by this server
					throw new MissingMetadataException("Missing Location of uuid#" + location.getUuid()
					        + " Make sure its present before Merging");
				}
			}
		}
		
		// The Form should already be existing
		Form form = enc.getForm();
		if (form != null) {
			if (form.getUuid() != null) {
				Form existingForm = Context.getFormService().getFormByUuid(form.getUuid());
				if (existingForm != null) {
					enc.setForm(existingForm);
				} else {
					throw new MissingMetadataException("Missing Form of uuid#" + form.getUuid()
					        + " Make sure its present before Merging");
				}
			}
		}
		
		// Update EncounterType
		EncounterType type = enc.getEncounterType();
		if (type != null) {
			if (type.getUuid() != null) {
				EncounterType existingType = Context.getEncounterService().getEncounterTypeByUuid(type.getUuid());
				if (existingType != null) {
					enc.setEncounterType(existingType);
				} else {
					throw new MissingMetadataException("Missing EncounterType of uuid#" + type.getUuid()
					        + " Make sure its present before Merging");
				}
			}
		}
		
		// Now Update The Obs About the Encounter Modifications
		Set<Obs> observations = enc.getObs();
		enc.setObs(null);
		enc = Context.getEncounterService().saveEncounter(enc);
		for (Obs obs : observations) {
			obs.setPerson(enc.getPatient());
			obs.setEncounter(new org.openmrs.Encounter(enc.getId()));
			if (obs.getId() != null) {
				Obs existingObs = Context.getObsService().getObsByUuid(obs.getUuid());
				if (existingObs != null) {
					obs.setUuid(null);
					inspectObsPropertiesAndModifyIfRequired(obs, enc);
					//obs.setPerson(enc.getPatient());		
				} else {
					inspectObsPropertiesAndModifyIfRequired(obs, enc);
				}
			} else {
				inspectObsPropertiesAndModifyIfRequired(obs, enc);
			}
		}
		
		enc.setObs(observations);
		log.debug("Ended Inspecting Encounter properties");
		return enc;
	}
	
	private Obs inspectObsPropertiesAndModifyIfRequired(Obs obs, org.openmrs.Encounter enc) {
		log.debug("Inspecting Obs " + obs);
		obs.setId(null);
		// Since an Encounter is for one specific Patient, lets assume also the Obs is for one Patient
		obs.setPerson(new Person(enc.getPatient().getId()));
		inspectConceptPropertiesAndModifyIfRequired(obs);
		ConceptName name = obs.getValueCodedName();
		if (name != null) {
			ConceptName existingName = Context.getConceptService().getConceptNameByUuid(name.getUuid());
			if (existingName != null) {
				obs.setValueCodedName(existingName);
			} else {
				// Means this concept name isn't known to this server
				// TODO Implement something better
				throw new MissingMetadataException("Missing ConceptName of uuid#" + name.getUuid()
				        + " Make sure its present before Merging");
			}
		}
		return obs;
	}
	
	private void inspectConceptPropertiesAndModifyIfRequired(Obs obs) {
		log.debug("Inspecting Concepts of " + obs);
		if (obs.getConcept() != null) {
			Concept existingConcept = Context.getConceptService().getConceptByUuid(obs.getConcept().getUuid());
			System.out.println("Returned Concept with " + obs.getConcept().getUuid() + " is " + existingConcept);
			if (existingConcept != null) {
				obs.setConcept(existingConcept);
				
			} else {
				// Means this concept isn't known to this server
				// TODO Implement something better
				throw new MissingMetadataException("Missing Concept of uuid#" + obs.getConcept().getUuid()
				        + " Make sure its present before Merging");
			}
		}
		
		if (obs.getValueCoded() != null) {
			Concept existingConcept = Context.getConceptService().getConceptByUuid(obs.getValueCoded().getUuid());
			if (existingConcept != null) {
				obs.setValueCoded(existingConcept);
				
			} else {
				// Means this concept isn't known to this server
				// TODO Implement something better
				throw new MissingMetadataException("Missing Concept of uuid#" + obs.getValueCoded().getUuid()
				        + " Make sure its present before Merging");
			}
		}
	}
}
