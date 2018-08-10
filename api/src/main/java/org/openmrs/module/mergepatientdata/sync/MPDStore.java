package org.openmrs.module.mergepatientdata.sync;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.module.mergepatientdata.enums.MergeAbleDataCategory;
import org.openmrs.module.mergepatientdata.resource.Encounter;
import org.openmrs.module.mergepatientdata.resource.Obs;
import org.openmrs.module.mergepatientdata.resource.Patient;

public class MPDStore {

	public MPDStore() {}
	
	public String originId;
	public List<Patient> patients;
	public List<Encounter> encounters;
	public List<Obs> obs;
	public List<MergeAbleDataCategory> types = new ArrayList<>();
	
	public List<MergeAbleDataCategory> getTypes() {
		return types;
	}
	public void setTypes(List<MergeAbleDataCategory> types) {
		this.types = types;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	public List<Obs> getObs() {
		return obs;
	}
	public void setObs(List<Obs> obs) {
		this.obs = obs;
	}
	public void addType(MergeAbleDataCategory type) {
		this.types.add(type);
	}
	public boolean hastData() {
		if (this.patients != null || this.obs != null) {
			if (!this.patients.isEmpty() || !this.obs.isEmpty() || !this.encounters.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public List<Encounter> getEncounters() {
		return encounters;
	}
	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}

	//TODO :- Add other Resources
		
}
