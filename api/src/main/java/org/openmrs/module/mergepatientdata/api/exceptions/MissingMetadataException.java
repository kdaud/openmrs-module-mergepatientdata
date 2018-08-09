package org.openmrs.module.mergepatientdata.api.exceptions;

public class MissingMetadataException extends RuntimeException {
	
	public MissingMetadataException(String message) {
		super(message);
	}
	
	public MissingMetadataException(Throwable cause) {
		super(cause);
	}
	
	public MissingMetadataException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
