package org.openmrs.module.mergepatientdata.resource;

import java.util.Date;

import org.openmrs.BaseOpenmrsObject;

public class ConceptNameTag implements MergeAbleResource {
	
	private Integer conceptNameTagId;
	
	private String tag;
	
	private String description;
	
	private Date dateCreated;
	
	private Boolean voided = false;
	
	private Date dateVoided;
	
	private String voidReason;
	
	private Date dateChanged;
	
	public ConceptNameTag() {
	}
	
	public ConceptNameTag(org.openmrs.ConceptNameTag tag) {
		this.conceptNameTagId = tag.getConceptNameTagId();
		this.tag = tag.getTag();
		this.description = tag.getDescription();
		this.dateCreated = tag.getDateCreated();
		this.voided = tag.getVoided();
		if (voided) {
			// Set to Super User. Voided by
		}
		this.dateVoided = tag.getDateVoided();
		this.dateChanged = tag.getDateChanged();
	}
	
	@Override
	public BaseOpenmrsObject getOpenMrsObject() {
		org.openmrs.ConceptNameTag tag = new org.openmrs.ConceptNameTag();
		tag.setConceptNameTagId(conceptNameTagId);
		tag.setTag(this.tag);
		tag.setDescription(description);
		tag.setVoided(voided);
		if (this.voided) {
			// Set voidedBy to Super User.
		}
		tag.setDateVoided(dateVoided);
		tag.setDateChanged(dateChanged);
		return tag;
	}
	
	public Integer getConceptNameTagId() {
		return conceptNameTagId;
	}
	
	public void setConceptNameTagId(Integer conceptNameTagId) {
		this.conceptNameTagId = conceptNameTagId;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Boolean getVoided() {
		return voided;
	}
	
	public void setVoided(Boolean voided) {
		this.voided = voided;
	}
	
	public Date getDateVoided() {
		return dateVoided;
	}
	
	public void setDateVoided(Date dateVoided) {
		this.dateVoided = dateVoided;
	}
	
	public String getVoidReason() {
		return voidReason;
	}
	
	public void setVoidReason(String voidReason) {
		this.voidReason = voidReason;
	}
	
	public Date getDateChanged() {
		return dateChanged;
	}
	
	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}
	
}
