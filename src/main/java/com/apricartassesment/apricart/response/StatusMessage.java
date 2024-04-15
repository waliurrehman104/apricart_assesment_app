package com.apricartassesment.apricart.response;

public enum StatusMessage {

	STATUS_0("Error"), STATUS_200("OK"), STATUS_201("Created"), STATUS_204("No Content"), STATUS_400("Bad Request"),
	STATUS_404("Not Found"), STATUS_408("Request Time Out"), STATUS_409("Conflict"),
	STATUS_500("Internal Server Error");

	private String mStatusMessage;

	private StatusMessage(String statusMessage) {
		mStatusMessage = statusMessage;
	}

	public String getStatusMessage() {
		return mStatusMessage;
	}

	@Override
	public String toString() {
		return mStatusMessage;
	}

	public static StatusMessage fromString(String statusMessage) {
		if (statusMessage != null) {
			for (StatusMessage value : StatusMessage.values()) {
				if (statusMessage.equalsIgnoreCase(value.mStatusMessage)) {
					return value;
				}
			}
		}
		return null;
	}
}
