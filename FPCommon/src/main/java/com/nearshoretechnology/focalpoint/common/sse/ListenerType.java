package com.nearshoretechnology.focalpoint.common.sse;

public enum ListenerType {
	
	KEY,
	VALUE;
	
	public String prepareKey(String id){
		return this.name() + "-" + id;
	}
}
