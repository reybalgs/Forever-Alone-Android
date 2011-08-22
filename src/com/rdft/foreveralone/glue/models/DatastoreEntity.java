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
		DebugConfig.logWarning(TAG, "DatastoreEntity parameterless constructor called");
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

	/**
	 * This method should pack the object's data into a JSONObject.
	 * All subclasses of DatastoreEntity are required to override
	 * this.
	 *  
	 * @return the object's data in a JSONObject
	 * @throws JSONException
	 */
	public JSONObject toJSONObject() throws JSONException {
		DebugConfig.logError("DatastoreEntity",
				"CRITICAL - Unimplemented toJSONObject!");
		throw new Error();
	}
	
	/**
	 * This method should check whether the object contains
	 * sufficient data to be accepted by the server's APIs.
	 * This should always be overridden by subclasses of 
	 * this class.
	 * 
	 * @return whether this object has complete data
	 */
	public boolean isSendable() {
		DebugConfig.logWarning(TAG, "Somebody didn't override isSendable()...");
		return false;
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
		if (entityKey.equals(this.entityKey)) {
			DebugConfig.logInfo(TAG, "Entity key is unchanged: " + entityKey);
		}
		this.entityKey = entityKey;
	}
}
