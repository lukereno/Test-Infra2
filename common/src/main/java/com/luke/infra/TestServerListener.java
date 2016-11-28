package com.luke.infra;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestServerListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
    	String testHost = System.getProperty("TEST_HOST");
		if (testHost == null) {
			throw new RuntimeException("\nTEST_HOST system property not set \nUse VM args -DTEST_HOST");
		}
        System.out.println("running testng onStart hook");
    }

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

//    @Override
//    public void onFinish(ISuite suite) {
//        try {
//            TestServerUtils.stopServer();
//        } catch (Exception e) { // NOSONAR
//            System.err.println(" Unable to stop test server");
//        }
//
//    }

}
