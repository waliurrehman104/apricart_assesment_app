package com.apricartassesment.apricart.response;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ServiceResponse {

    private StatusCode mCode;
    private String mDescription;
    private Object mData;

    private static Map<StatusCode, String> statusMap = new HashMap<>();

    static {
        statusMap.put(StatusCode.STATUS_OK, "OK");
        statusMap.put(StatusCode.STATUS_CREATED, "Created");
        statusMap.put(StatusCode.STATUS_NO_CONTENT, "No Content");
        statusMap.put(StatusCode.STATUS_BAD_REQUEST, "Bad Request");
        statusMap.put(StatusCode.STATUS_NOT_FOUND, "Not Found");
        statusMap.put(StatusCode.STATUS_CONFLICT, "Conflict");
        statusMap.put(StatusCode.STATUS_INTERNAL_SERVER_ERROR, "Internal Server Error");
        statusMap.put(StatusCode.STATUS_REQUEST_TIME_OUT, "Request Time Out");
        statusMap.put(StatusCode.STATUS_ERROR, "Error");
    }

    public ServiceResponse(StatusCode code, String description, Object data) {
        mCode = code;
        mDescription = description;
        mData = data;

        if (!statusMap.containsKey(mCode)) {
            throw new NoSuchElementException("Status code '" + code.getStatusCode() + "' not available");
        }
    }

    public String generateResponse() {
        StringBuilder response = new StringBuilder();

        response.append("Status: ").append(mCode.getStatusCode()).append(" ").append(statusMap.get(mCode));
        if (mDescription != null) {
            response.append("\nDescription: ").append(mDescription);
        }

        if (mData != null) {
            response.append("\nData: ").append(mData.toString());
        }

        return response.toString();
    }

}
