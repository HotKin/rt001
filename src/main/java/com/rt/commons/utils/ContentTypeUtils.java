package com.rt.commons.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 
 * Describe:
 * 	获取所有ContentType
 * @Author CAO KUN
 * @Date 2018-12-18 17:24:36
 *
 */
public class ContentTypeUtils {
	
	public static Map<String, String> getAllContentTypes() {
		Map<String, String> contentTypes=new HashMap<String,String>();
		try {
			Document doc = Jsoup.connect("http://tool.oschina.net/commons").get();
			Elements el=doc.getElementsByTag("table");
			Elements elements=el.get(0).getElementsByTag("tbody");
			Element element=elements.get(1);
			Elements tr=element.getElementsByTag("tr");
			for (Element t : tr) {
				String hz0=t.select("td").get(0).text();
				if(StringUtils.equals(".*（ 二进制流，不知道下载文件类型）", hz0)) {
					hz0=".*";
				}
				String ct0=t.select("td").get(1).text();
				
				String hz1=t.select("td").get(2).text();
				String ct1=t.select("td").get(3).text();
				
				contentTypes.put(hz0, ct0);
				contentTypes.put(hz1, ct1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentTypes;
	}
	
	public static void main(String[] args) {
		getAllContentTypes();
	}
}
