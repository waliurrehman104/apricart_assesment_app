package com.apricartassesment.apricart.response;

public enum StatusCode {

	/**
	 * While adding new statuses, the newly added status need to be added in the
	 * statusMap in ServiceResponse so that it can append proper message according
	 * to the status
	 */

	STATUS_OK(200), STATUS_CREATED(201), STATUS_NO_CONTENT(204), STATUS_BAD_REQUEST(400), STATUS_NOT_FOUND(404),
	STATUS_REQUEST_TIME_OUT(408), STATUS_CONFLICT(409), STATUS_ERROR(0), STATUS_INTERNAL_SERVER_ERROR(500);

	private int mStatusCode;

	private StatusCode(int statusCode) {
		mStatusCode = statusCode;
	}

	public int getStatusCode() {
		return mStatusCode;
	}

	public static StatusCode fromInt(int statusCode) {
		for (StatusCode value : StatusCode.values()) {
			if (statusCode == value.mStatusCode) {
				return value;
			}
		}
		return null;
	}

}
