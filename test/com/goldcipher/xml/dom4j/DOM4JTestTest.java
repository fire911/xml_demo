package com.goldcipher.xml.dom4j;

import static org.junit.Assert.*;

import java.io.IOException;

import org.dom4j.DocumentException;
import org.junit.BeforeClass;
import org.junit.Test;

public class DOM4JTestTest {
	
	private static DOM4JTest dom4jTest=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dom4jTest=new DOM4JTest();
	}

	@Test
	public void testParseXML() {
		try {
			dom4jTest.parseXML();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateXML() {
		try {
			dom4jTest.createXML();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
