package org.openmrs.module.mergepatientdata.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.mergepatientdata.api.impl.MergePatientDataConfigurationServiceImpl;
import org.openmrs.module.mergepatientdata.api.impl.MergePatientDataExportServiceImpl;
import org.openmrs.module.mergepatientdata.api.model.audit.PaginatedAuditMessage;
import org.openmrs.module.mergepatientdata.api.model.config.MPDConfiguration;
import org.openmrs.module.mergepatientdata.enums.Operation;
import org.openmrs.module.mergepatientdata.resource.Concept;
import org.openmrs.module.mergepatientdata.resource.Encounter;
import org.openmrs.module.mergepatientdata.resource.Location;
import org.openmrs.module.mergepatientdata.resource.Obs;
import org.openmrs.module.mergepatientdata.resource.Patient;
import org.openmrs.module.mergepatientdata.resource.Person;
import org.openmrs.module.mergepatientdata.sync.MPDClient;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

public class MergePatientDataExportServiceTest extends BaseModuleContextSensitiveTest {
	
	List<Class> typesToExport;
	
	MergePatientDataExportService exportService;
	
	PaginatedAuditMessage auditor;
	
	@Autowired
	MPDClient client;
	
	@Before
	public void setup() {
		auditor = new PaginatedAuditMessage();
		auditor.setResources(new ArrayList<>());
		auditor.setFailureDetails(new ArrayList<>());
		auditor.setOperation(Operation.EXPORT);
		typesToExport = new ArrayList<>();
		typesToExport.add(Patient.class);
		exportService = new MergePatientDataExportServiceImpl();
	}
	
	/**
	 * TODO : Some mocking required to add more meaning to this TestCase.
	 */
	//@Test
	public void exportMergeAblePatientData_shouldExportSerializedPatientDataToDummyFile() {
		File encryptedFile = exportService.exportMergeAblePatientData(typesToExport, auditor, "Dummy_Server");
		Assert.assertTrue(encryptedFile.isFile());
	}
	
	@Test
	public void exportMergeAblePatientData_shouldAlsoExportBothObsAndEncounterDataToDummyFile() {
		typesToExport.add(Encounter.class);
		typesToExport.add(Obs.class);
		org.openmrs.Obs obs = Context.getObsService().getObs(10);
		// modify the obs
		obs.setEncounter(null);
		obs.setValueText("Testing Patient");
		Context.getObsService().saveObs(obs, "This Obs should be Patient Note");
		File encryptedFile = exportService.exportMergeAblePatientData(typesToExport, auditor, "Dummy_Server");
		Assert.assertTrue(encryptedFile.isFile());
	}
	
	//@Test
	public void exportMergeAblePatientData_shouldAlsoExportEncountersToDummyFile() throws Exception {
		typesToExport.add(Encounter.class);
		File encryptedFile = exportService.exportMergeAblePatientData(typesToExport, auditor, "Dummy_Server");
		Assert.assertTrue(encryptedFile.isFile());
	}
}
