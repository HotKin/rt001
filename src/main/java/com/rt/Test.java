package com.rt;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("http://tool.oschina.net/commons").get();
			Elements el=doc.getElementsByTag("table");
			Elements elements=el.get(0).getElementsByTag("tbody");
			Element element=elements.get(1);
			Elements tr=element.getElementsByTag("tr");
			System.out.println("后缀\tContent-Type\t");
			for (Element t : tr) {
				String hz0=t.select("td").get(0).text();
				String ct0=t.select("td").get(1).text();
				
				String hz1=t.select("td").get(2).text();
				String ct1=t.select("td").get(3).text();
				
				System.out.println(hz0+"\t"+ct0);
				System.out.println(hz1+"\t"+ct1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
