package com.goldcipher.xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOM解析/创建XML
 * 
 * @author lene
 *
 */
public class DomTest {
	/**
	 * 获取DocumentBuilder对象
	 * 
	 * @return
	 * @throws ParserConfigurationException
	 */
	public static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
		// 创建DocumentBuilderFactory对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 通过DocumentBuilderFactory创建DocumentBuilder对象
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db;
	}

	/**
	 * DOM解析XML
	 * 
	 * @param args
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void parseXML() throws ParserConfigurationException, SAXException, IOException {
		// 通过DocumentBuilder的parse方法创建Document对象
		Document doc = getDocumentBuilder().parse("src/books.xml");
		// 通过标签名称获取NodeList列表
		NodeList books = doc.getElementsByTagName("book");
		// 在不知道跟目录的情况下遍历xml
		// Element d=doc.getDocumentElement();
		// NodeList li=d.getChildNodes();
		// for (int i = 0; i < li.getLength(); i++) {
		// if(li.item(i).getNodeType()==Node.TEXT_NODE){
		// continue;
		// }
		// System.out.println(li.item(i));
		// }
		// 遍历NodeList
		for (int i = 0; i < books.getLength(); i++) {
			// 获取NodeList中的第i+1个节点
			// Node book = books.item(i);
			// // 在不知道节点属性名的时候，使用Node的getAttributes方法获取属性列表
			// NamedNodeMap attrs = book.getAttributes();
			// for (int j = 0; j < attrs.getLength(); j++) {
			// // 获取NamedNodeMap中的第j+1个属性
			// Node attr = attrs.item(j);
			// System.out.println(
			// "第" + (i + 1) + "个节点的第" + (j + 1) + "个属性：" + attr.getNodeName() +
			// "=" + attr.getNodeValue());
			// }
			// 已经知道book节点的属性名，并且该属性有且只有一个
			Element book = (Element) books.item(i);
			// 通过getAttribute(String name)获取属性值
			System.out.println("第" + (i + 1) + "个节点的id=" + book.getAttribute("id"));
			// 解析子节点
			NodeList childNodes = book.getChildNodes();
			// 节点类型分为Element，Attr，和Text
			System.out.println(book.getNodeName() + "总共有" + childNodes.getLength() + "个子节点");
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node child = childNodes.item(j);
				if (child.getNodeType() == Node.ELEMENT_NODE) {
					// 获取节点的文本信息
					// 方法1：使用child.getFirstChild().getNodeValue() 如果
					// child.getFirstChild()获取到的是一个Element那么getNodeValue()获取到的值为NULL
					// System.out.println(child.getNodeName()+"="+child.getFirstChild().getNodeValue());
					// 方法2：getTextContent返回此节点及其后代的文本内容
					System.out.println(child.getNodeName() + "=" + child.getTextContent());
				}

			}
		}
	}

	/**
	 * DOM方式创建XML
	 * 
	 * @throws ParserConfigurationException
	 * @throws TransformerException 
	 */
	public  void createXML() throws ParserConfigurationException, TransformerException {
		// 创建Document对象
		Document document = getDocumentBuilder().newDocument();
		//设置XML不显示standalone属性
		document.setXmlStandalone(true);
		// 创建bookStore根节点
		Element bookStore = document.createElement("bookStore");
		// 创建book节点
		Element book = document.createElement("book");
		// book节点添加属性
		book.setAttribute("id", "1");
		//创建name子节点
		Element name=document.createElement("name");
		//设置name标签的文本信息
		name.setTextContent("think in java");
		// 将name节点插入到book中
		book.appendChild(name);
		// 将book节点插入到bookStore中
		bookStore.appendChild(book);
		// 将bookStore添加到dom树中
		document.appendChild(bookStore);
		//创建TransformerFactory对象
		TransformerFactory tff=TransformerFactory.newInstance();
		//创建Transformer对象
		Transformer tf=tff.newTransformer();
		//设置内容是否换行
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		//生成文件
		tf.transform(new DOMSource(document),new StreamResult(new File("books1.xml")));
		
	}

}
