package com.luke.infra.platform.dataprovider;

import java.util.List;

import com.luke.infra.platform.TestContext;

/**
 * This interface defines the implementation for each data provider of each data format
 *
 */
public interface InfraDataProvider {

	public List<Object[]> getRows(TestContext ctx);
}
