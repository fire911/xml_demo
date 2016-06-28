package com.goldcipher.xml.sax;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.goldcipher.entity.Book;

/**
 * SAX方式解析XML
 * @author lene
 *
 */
public class SAXTest {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//创建SAXParserFactory的实例
		SAXParserFactory spf=SAXParserFactory.newInstance();
		//通过SAXParserFactory创建SAXParser的实例
		SAXParser parser=spf.newSAXParser();
		SAXParseHandler handler= new SAXParseHandler();
		parser.parse("src/books.xml", handler);
		for (Book book : handler.getBookList()) {
			System.out.println(book.toString());
		}
		
	}

}
