package com.goldcipher.xml.jdom;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.BeforeClass;
import org.junit.Test;

public class JDOMTestTest {
	
	private static JDOMTest jdomTest=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jdomTest=new JDOMTest();
	}

	@Test
	public void testParseXML() {
		try {
			jdomTest.parseXML();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateXML() {
		try {
			jdomTest.createXML();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
