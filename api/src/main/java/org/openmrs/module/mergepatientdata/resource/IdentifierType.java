package org.openmrs.module.mergepatientdata.resource;

import java.util.Objects;

import org.openmrs.BaseOpenmrsObject;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.context.Context;

public class IdentifierType implements MergeAbleResource {
	
	private String uuid;
	
	private String name;
	
	// private String description;
	
	public IdentifierType(PatientIdentifierType openmrsType) {
		this.uuid = openmrsType.getUuid();
		this.name = openmrsType.getName();
	}
	
	@Override
	public BaseOpenmrsObject getOpenMrsObject() {
		BaseOpenmrsObject type = Context.getPatientService().getPatientIdentifierTypeByUuid(uuid);
		if (type != null) {
			return type;
		}
		
		org.openmrs.PatientIdentifierType newType = new org.openmrs.PatientIdentifierType();
		newType.setUuid(uuid);
		return newType;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		
		IdentifierType that = (IdentifierType) o;
		return Objects.equals(uuid, that.uuid) && Objects.equals(name, that.name);
		// && Objects.equals(description, that.description);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(uuid, name);
	}
	
}
