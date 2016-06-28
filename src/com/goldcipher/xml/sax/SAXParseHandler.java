package com.goldcipher.xml.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.goldcipher.entity.Book;

public class SAXParseHandler extends DefaultHandler {
	
	String value=null;
	
	Book book=null;
	
	private List<Book> bookList=new ArrayList<Book>();

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("解析开始");
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("解析结束");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
//		System.out.println(qName+"标签共有"+attributes.getLength()+"个属性");
//		int num=attributes.getLength();
//		for (int i = 0; i < num; i++) {
//			System.out.println(qName+"标签的第"+(i+1)+"个属性"+attributes.getQName(i)+"="+attributes.getValue(i));
//		}
		if("book".equals(qName)){
			book=new Book();
			//已知标签属性名获取标签属性值
			//System.out.println(attributes.getValue("id"));
			//不知道属性名获取标签属性值
			int num=attributes.getLength();
			for (int i = 0; i < num; i++) {
//				System.out.println("book标签的第"+(i+1)+"个属性"+attributes.getQName(i)+"="+attributes.getValue(i));
				if(attributes.getQName(i).equals("id")){
					book.setId(attributes.getValue(i));
				}
			}
		}
//		System.out.print("节点名:"+qName+"----节点值:");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(qName.equals("book")){
			bookList.add(book);
			book=null;
		}
		switch(qName){
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
	
	/**
	 * 获取节点值
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		value=new String(ch, start, length);
//		if(!value.trim().equals("")){
//			System.out.println(value);
//		}
		
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}


	
}
