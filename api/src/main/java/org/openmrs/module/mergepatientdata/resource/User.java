package org.openmrs.module.mergepatientdata.resource;

import org.openmrs.BaseOpenmrsObject;

/**
 * Planning to come up with a better design. Merged data should all default to the Super User.
 */
public class User implements MergeAbleResource {
	
	private Integer userId;
	
	private String uuid;
	
	public User(Integer id, String uuid) {
		this.userId = id;
		this.uuid = uuid;
	}
	
	@Override
	public BaseOpenmrsObject getOpenMrsObject() {
		org.openmrs.User user = new org.openmrs.User(this.userId);
		user.setUuid(uuid);
		return user;
	}
	
}
