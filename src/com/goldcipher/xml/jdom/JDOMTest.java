package com.goldcipher.xml.jdom;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.goldcipher.entity.Book;

/**
 * JDOM方式解析XML
 * @author lene
 *
 */
public class JDOMTest {
	

	public static void main(String[] args) throws JDOMException, IOException {
		//创建SAXBuilder对象
		SAXBuilder builder=new SAXBuilder();
		//创建InputStream
		InputStream in=new FileInputStream("src/books.xml");
		//使用SAXBuilder的build方法
		Document document=builder.build(in);
		//获取根节点
		Element element=document.getRootElement();
		//获取根节点的子节点(也就是本例中的book节点)
		List<Element> books=element.getChildren();
		//遍历子节点 获取子节点的节点名 属性名及属性值 
		for (Element book : books) {
			Book bk=new Book(); 
			System.out.println("==============开始解析XML==============");
//			int num=books.indexOf(book)+1;
			//知道属性名获取属性值
			System.out.println(book.getAttributeValue("id"));
			bk.setId(book.getAttributeValue("id"));
			//获取属性列表(不知道节点属性获取节点属性名和属性值的方式)
//			List<Attribute> attrs=book.getAttributes();
//			for (Attribute attr : attrs) {
//				System.out.println("第"+num+"本"+book.getName()+"的"+attr.getName()+"属性="+attr.getValue());
//			}
			//解析子节点
			List<Element> childs=book.getChildren();
			for (Element child : childs) {
				System.out.println("节点名："+child.getName()+"----节点值："+child.getValue());
				String name=child.getName();
				String value=child.getValue();
				switch(name){
				case "name":
					bk.setName(value);
					break;
				case "author":
					bk.setAuthor(value);
					break;
				case "price":
					bk.setPrice(value);
					break;
				case "year":
					bk.setYear(value);
					break;
				case "language":
					bk.setLanguage(value);
					break;
				}
			}
			System.out.println(bk.toString());
			System.out.println("==============结束解析XML==============");
		}
		in.close();
	}

}
