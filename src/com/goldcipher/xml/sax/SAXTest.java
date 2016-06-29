package com.goldcipher.xml.sax;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.goldcipher.entity.Book;

/**
 * SAX方式解析/创建XML
 * 
 * @author lene
 *
 */
public class SAXTest {
	/**
	 * 使用SAX方式解析XML
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public List<Book> parseXML() throws ParserConfigurationException, SAXException, IOException {
		// 创建SAXParserFactory的实例
		SAXParserFactory spf = SAXParserFactory.newInstance();
		// 通过SAXParserFactory创建SAXParser的实例
		SAXParser parser = spf.newSAXParser();
		SAXParseHandler handler = new SAXParseHandler();
		parser.parse("src/books.xml", handler);
		// for (Book book : handler.getBookList()) {
		// System.out.println(book.toString());
		// }
		return handler.getBookList();
	}

	// 使用SAX方式创建XML
	public void createXML() throws TransformerConfigurationException, IOException, SAXException, ParserConfigurationException {
		List<Book> bookList=this.parseXML();
		// 1.创建SAXTransformerFactory对象
		SAXTransformerFactory sff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		// 2.获取TransformerHandler对象
		TransformerHandler handler = sff.newTransformerHandler();
		// 3.获取Transformer对象
		Transformer tr = handler.getTransformer();
		// 通过Transformer对xml文件进行设置
		// 设置编码
		tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		// 设置是否换行
		tr.setOutputProperty(OutputKeys.INDENT, "yes");
		File file = new File("sax_create.xml");
		if (!file.exists()) {
			file.createNewFile();
		}
		// 4.创建StreamResult对象
		StreamResult result = new StreamResult(new FileOutputStream(file));
		//5.使result与handler关联
		handler.setResult(result);
		//6.使用handler对象对文件内容进行编写
		handler.startDocument();
		AttributesImpl atts=new AttributesImpl();
		handler.startElement("", "", "bookStore", atts);
		for (Book book : bookList) {
			atts.addAttribute("", "", "id","",book.getId());
			handler.startElement("", "", "book", atts);
			atts.clear();
			handler.startElement("", "", "name",atts);
			handler.characters(book.getName().toCharArray(),0, book.getName().length());
			handler.endElement("","","name");
			handler.endElement("","","book");
		}
		handler.endElement("","","bookStore");
		handler.endDocument();
	}

}
