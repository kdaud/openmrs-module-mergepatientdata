package org.openmrs.module.mergepatientdata.api;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.mergepatientdata.api.impl.ObsResourceServiceImpl;
import org.openmrs.module.mergepatientdata.api.model.audit.PaginatedAuditMessage;
import org.openmrs.module.mergepatientdata.resource.Obs;

public class ObsResourceServiceTest {
	
	ObsResourceService service;
	
	PaginatedAuditMessage auditor;
	
	public void setup() {
		service = new ObsResourceServiceImpl();
		auditor = new PaginatedAuditMessage();
	}
	
	public void saveObservations_shouldSaveObs() {
		// Get an existing Obs
		Obs obs = new Obs(Context.getObsService().getObs(10), true);
		// Change its uuid
		obs.setUuid("57a68666-5067kh-11de-862cb-001e37878nb60e");
		// Set encounterId to null
		obs.setEncounter(null);
		List<Obs> obsItems = new ArrayList<>();
		obsItems.add(obs);
		service.saveObservations(obsItems, auditor);
	}
}
