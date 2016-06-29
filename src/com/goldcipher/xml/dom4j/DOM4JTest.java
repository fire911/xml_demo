package com.goldcipher.xml.dom4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.goldcipher.entity.Book;

/**
 * DOM4J解析XML
 * 
 * @author lene
 *
 */
public class DOM4JTest {

	/**
	 * DOM4J解析XML
	 * 
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public void parseXML() throws DocumentException {
		// 创建SAXReader对象
		SAXReader reader = new SAXReader();
		// 通过SAXReader对象加载文件，并获取Document对象
		Document document = reader.read(new File("src/books.xml"));
		// 获取根节点
		Element bookStore = document.getRootElement();
		// 通过bookStore获取子节点（也就是当前事例的book节点）
		Iterator<Element> it = bookStore.elementIterator();
		while (it.hasNext()) {
			Book book = new Book();
			Element element = it.next();
			// 知道属性名的获取方式
			book.setId(element.attributeValue("id"));
			// 获取元素属性 不知道属性名的获取方式
			// List<Attribute> attrs=element.attributes();
			// for (Attribute attr : attrs) {
			// System.out.println("属性名："+attr.getName()+"---属性值："+attr.getValue());
			// }
			// 遍历子节点
			Iterator<Element> itt = element.elementIterator();
			while (itt.hasNext()) {
				Element bookChild = itt.next();
				System.out.println("节点名：" + bookChild.getName() + "---节点值：" + bookChild.getStringValue());
				String name = bookChild.getName();
				String value = bookChild.getStringValue();
				switch (name) {
				case "name":
					book.setName(value);
					break;
				case "author":
					book.setAuthor(value);
					break;
				case "price":
					book.setPrice(value);
					break;
				case "year":
					book.setYear(value);
					break;
				case "language":
					book.setLanguage(value);
					break;
				}
			}
			System.out.println(book.toString());
		}
	}
	/**
	 * 使用DOM4J生成XML
	 * @throws IOException
	 */
	public  void createXML() throws IOException{
		//1.创建Document对象
		Document document=DocumentHelper.createDocument();
		//为Document添加rss节点
		Element rss=document.addElement("rss");
		//为rss节点添加属性
		rss.addAttribute("version", "2.0");
		Element channel=rss.addElement("channel");
		Element title=channel.addElement("title");
		title.setText("<!CDATA[[DOM4J创建RSS]]>");
		File file=new File("rss_dom4j.xml");
		//设置换行与缩进
		OutputFormat format=OutputFormat.createPrettyPrint();
		//设置编码
		format.setEncoding("GBK");
		//使用xmlWriter创建XML 
		XMLWriter xw=new XMLWriter(new FileOutputStream(file), format);
		//设置是否转译特殊字符 默认为true
		xw.setEscapeText(false);
		xw.write(document);
		xw.close();
	}
}
