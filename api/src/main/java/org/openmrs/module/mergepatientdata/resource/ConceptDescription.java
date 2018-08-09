package org.openmrs.module.mergepatientdata.resource;

import java.util.Date;
import java.util.Locale;

import org.openmrs.BaseOpenmrsObject;

@Deprecated
public class ConceptDescription implements MergeAbleResource {
	
	private Integer conceptDescriptionId;
	
	private Concept concept;
	
	private String description;
	
	private Locale locale;
	
	private Date dateCreated;
	
	private Date dateChanged;
	
	public ConceptDescription(org.openmrs.ConceptDescription desc) {
		this.conceptDescriptionId = desc.getConceptDescriptionId();
		//this.concept = new Concept(desc.getConcept(), false);
		this.description = desc.getDescription();
		this.locale = desc.getLocale();
		this.dateCreated = desc.getDateCreated();
		this.dateChanged = desc.getDateChanged();
		// Set creator to Super User
		
	}
	
	@Override
	public BaseOpenmrsObject getOpenMrsObject() {
		org.openmrs.ConceptDescription desc = new org.openmrs.ConceptDescription();
		desc.setConceptDescriptionId(conceptDescriptionId);
		desc.setConcept((org.openmrs.Concept) concept.getOpenMrsObject());
		desc.setDescription(description);
		desc.setLocale(locale);
		desc.setDateChanged(dateChanged);
		desc.setDateCreated(dateCreated);
		return desc;
	}
	
	public Integer getConceptDescriptionId() {
		return conceptDescriptionId;
	}
	
	public void setConceptDescriptionId(Integer conceptDescriptionId) {
		this.conceptDescriptionId = conceptDescriptionId;
	}
	
	public Concept getConcept() {
		return concept;
	}
	
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}
