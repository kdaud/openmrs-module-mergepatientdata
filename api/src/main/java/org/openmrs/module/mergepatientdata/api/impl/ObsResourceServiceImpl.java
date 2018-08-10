/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.mergepatientdata.api.impl;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.api.context.Context;
import org.openmrs.module.mergepatientdata.api.ObsResourceService;
import org.openmrs.module.mergepatientdata.api.exceptions.MissingMetadataException;
import org.openmrs.module.mergepatientdata.api.model.audit.PaginatedAuditMessage;
import org.openmrs.module.mergepatientdata.api.utils.ObjectUtils;
import org.openmrs.module.mergepatientdata.resource.Obs;

public class ObsResourceServiceImpl implements ObsResourceService {
	
	@Override
	public org.openmrs.Obs saveObs(org.openmrs.Obs obs) {
		return Context.getObsService().saveObs(obs, null);
	}
	
	@Override
	public void saveObservations(List<Obs> mpdObs, PaginatedAuditMessage auditor) {
		auditor.getResources().add("Obs");
		
		if (mpdObs.isEmpty()) {
			return;
		}
		List<org.openmrs.Obs> observations = (List<org.openmrs.Obs>) ObjectUtils
		        .getOpenmrsResourceObjectsFromMPDResourceObjects(mpdObs);
		Integer counter = 0;
		for (org.openmrs.Obs obs : observations) {
			org.openmrs.Obs existingObs = Context.getObsService().getObsByUuid(obs.getUuid());
			if (existingObs != null) {
				// Then the Obs already exists
				// TODO cater for updating
				continue;
			}
			obs.setId(null);
			Concept existingConcept = Context.getConceptService().getConceptByUuid(obs.getConcept().getUuid());
			if (existingConcept != null) {
				obs.setConcept(existingConcept);
			} else {
				throw new MissingMetadataException("Required Concept with UUID#" + obs.getConcept().getUuid()
				        + " Not found. Make sure its present and try again!");
			}
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
			org.openmrs.Obs savedObs = saveObs(obs);
			if (savedObs != null) {
				counter++;
			}
		}
		auditor.getResourceCount().put("Obs", counter);
	}
	
}
