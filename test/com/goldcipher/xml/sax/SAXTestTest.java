package com.goldcipher.xml.sax;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

public class SAXTestTest {
	
	private static SAXTest saxTest=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		saxTest=new SAXTest();
	}

	@Test
	public void testParseXML() {
		try {
			saxTest.parseXML();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}

	@Test
	public void testCreateXML() {
		try {
			saxTest.createXML();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}
