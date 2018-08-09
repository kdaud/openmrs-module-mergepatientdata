package org.openmrs.module.mergepatientdata.resource;

import org.openmrs.BaseOpenmrsObject;

// By default all Users should be Super Users
public class Concept implements MergeAbleResource {
	
	private Integer conceptId;
	
	private String uuid;
	
	public Concept() {
	}
	
	public Concept(Integer id, String uuid) {
		this.uuid = uuid;
		this.conceptId = id;
	}
	
	@Override
	public BaseOpenmrsObject getOpenMrsObject() {
		org.openmrs.Concept openmrsConcept = new org.openmrs.Concept();
		openmrsConcept.setConceptId(conceptId);
		openmrsConcept.setUuid(uuid);
		return openmrsConcept;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
