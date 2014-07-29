package com.bhnetwork.service;

public enum PORDataSyncServiceStatus {

	ACK("ACK"),
	OK("OK");
	
	private final String value;
	
	PORDataSyncServiceStatus(String v){
		value = v;
	}
	
	public String value() {
        return value;
    }

    public static PORDataSyncServiceStatus fromValue(String v) {
        for (PORDataSyncServiceStatus c: PORDataSyncServiceStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
