package com.rdft.foreveralone.glue.models;

import org.json.JSONException;
import org.json.JSONObject;

import com.rdft.foreveralone.glue.debug.DebugConfig;

public class DatastoreEntity {
	private String entityKey;
	private String TAG = "DatastoreEntity";

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
		DebugConfig.logError("DatastoreEntity",
				"CRITICAL - Unimplemented toJSONObject!");
		throw new Error();
	}

	public String getEntityKey() {
		return entityKey;
	}

	public void setEntityKey(String entityKey) {
		/*
		 * The entity key is the App Engine datastore's identifier for instances
		 * of an object.
		 * 
		 * Don't allow the entity key to be modified if we already have one.
		 */
		if (this.entityKey != null) {
			DebugConfig.logInfo(TAG, "WARNING: Replacing an existing entity key!");
		}
		this.entityKey = entityKey;
	}
}
