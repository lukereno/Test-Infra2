package com.luke.infra.platform.dataprovider;

public enum DataProviderType {
	CSV ("CSV");
	
	private final String providerType;

	DataProviderType(String providerType) {
        this.providerType = providerType;
    }
    
    public String getProviderType() {
        return this.providerType;
    }
}
