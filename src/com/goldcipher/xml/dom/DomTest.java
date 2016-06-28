package com.goldcipher.xml.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOM解析XML
 * 
 * @author lene
 *
 */
public class DomTest {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// 创建DocumentBuilderFactory对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 通过DocumentBuilderFactory创建DocumentBuilder对象
		DocumentBuilder db = dbf.newDocumentBuilder();
		// 通过DocumentBuilder的parse方法创建Document对象
		Document doc = db.parse("src/books.xml");
		// 通过标签名称获取NodeList列表
		NodeList books = doc.getElementsByTagName("book");
		//在不知道跟目录的情况下遍历xml
//		Element d=doc.getDocumentElement();
//		NodeList li=d.getChildNodes();
//		for (int i = 0; i < li.getLength(); i++) {
//			if(li.item(i).getNodeType()==Node.TEXT_NODE){
//				continue;
//			}
//			System.out.println(li.item(i));
//		}
		// 遍历NodeList
		for (int i = 0; i < books.getLength(); i++) {
			// 获取NodeList中的第i+1个节点
//			Node book = books.item(i);
//			// 在不知道节点属性名的时候，使用Node的getAttributes方法获取属性列表
//			NamedNodeMap attrs = book.getAttributes();
//			for (int j = 0; j < attrs.getLength(); j++) {
//				// 获取NamedNodeMap中的第j+1个属性
//				Node attr = attrs.item(j);
//				System.out.println(
//						"第" + (i + 1) + "个节点的第" + (j + 1) + "个属性：" + attr.getNodeName() + "=" + attr.getNodeValue());
//			}
			//已经知道book节点的属性名，并且该属性有且只有一个
			Element book = (Element) books.item(i);
			//通过getAttribute(String name)获取属性值
			System.out.println("第" + (i + 1) + "个节点的id=" +book.getAttribute("id"));
			//解析子节点
			NodeList childNodes=book.getChildNodes();
			//节点类型分为Element，Attr，和Text 
			System.out.println(book.getNodeName()+"总共有"+childNodes.getLength()+"个子节点");
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node child=childNodes.item(j);
				if(child.getNodeType()==Node.ELEMENT_NODE){
					//获取节点的文本信息
					//方法1：使用child.getFirstChild().getNodeValue()	如果 child.getFirstChild()获取到的是一个Element那么getNodeValue()获取到的值为NULL
//					System.out.println(child.getNodeName()+"="+child.getFirstChild().getNodeValue());
					//方法2：getTextContent返回此节点及其后代的文本内容
					System.out.println(child.getNodeName()+"="+child.getTextContent());
				}
				
			}
		}
	}

}
