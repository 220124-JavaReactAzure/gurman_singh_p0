package com.revature.course_reg.util;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class ConnectionFactoryTestSuite {

	@Test
	public void test_getConnection_returnValidConnection_givenProvidedCredentials() {
		try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
			System.out.println(conn);
			Assert.assertNotNull(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}