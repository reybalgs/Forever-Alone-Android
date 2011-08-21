package com.rdft.foreveralone.glue.models;

import org.json.JSONException;
import org.json.JSONObject;

import com.rdft.foreveralone.glue.CommHandler;
import com.rdft.foreveralone.glue.CommHandler.ISendable;
import com.rdft.foreveralone.glue.debug.DebugConfig;

public class DatastoreEntity {
	private String entityKey;
	
	public String getAPIPath() {
		return "/api/nope";
	}
	
	public DatastoreEntity() {
		
	}
	
	public DatastoreEntity(JSONObject jObj) throws JSONException {
		setEntityKey(jObj.getString("entityKey"));
	}
	
	protected JSONObject safeGet(JSONObject jObj, String key) {
		try {
			return jObj.getJSONObject(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	public JSONObject toJSONObject() throws JSONException {
		DebugConfig.logError("DatastoreEntity", "CRITICAL - Unimplemented toJSONObject!");
		throw new Error();
	}

	public String getEntityKey() {
		return entityKey;
	}

	protected void setEntityKey(String entityKey) {
		// Don't allow the entity key to be modified if we already have one
		if (this.entityKey == null) {
			this.entityKey = entityKey;
		} else {
			EntityKeyModificationError up = new EntityKeyModificationError();
			throw up;
		}
	}
	
	public class EntityKeyModificationError extends Error {
		private static final long serialVersionUID = 4438270464200017874L;
	}
}
