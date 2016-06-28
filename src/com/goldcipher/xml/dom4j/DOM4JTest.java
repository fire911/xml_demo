package com.goldcipher.xml.dom4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.goldcipher.entity.Book;

/**
 * DOM4J解析XML
 * @author lene
 *
 */
public class DOM4JTest {
	

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws DocumentException {
		//创建SAXReader对象
		SAXReader reader=new SAXReader();
		//通过SAXReader对象加载文件，并获取Document对象
		 Document document= reader.read(new File("src/books.xml"));
		 //获取根节点
		 Element bookStore=document.getRootElement();
		 //通过bookStore获取子节点（也就是当前事例的book节点）
		 Iterator<Element> it=bookStore.elementIterator();
		 while(it.hasNext()){
			 Book book=new Book();
			 Element element=it.next();
			 //知道属性名的获取方式
			 book.setId(element.attributeValue("id"));
			 //获取元素属性 不知道属性名的获取方式
//			 List<Attribute> attrs=element.attributes();
//			 for (Attribute attr : attrs) {
//				System.out.println("属性名："+attr.getName()+"---属性值："+attr.getValue());
//			 }
			 //遍历子节点
			 Iterator<Element> itt=element.elementIterator();
			 while(itt.hasNext()){
				 Element bookChild=itt.next();
				 System.out.println("节点名："+bookChild.getName()+"---节点值："+bookChild.getStringValue());
				 String name=bookChild.getName();
				 String value=bookChild.getStringValue();
				 switch(name){
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

}
