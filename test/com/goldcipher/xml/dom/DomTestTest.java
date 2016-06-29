package com.goldcipher.xml.dom;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

public class DomTestTest {
	
	private static DomTest domTest=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 domTest=new DomTest();
	}

	@Test
	public void testParseXML() {
		try {
			domTest.parseXML();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateXML() {
		try {
			domTest.createXML();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
