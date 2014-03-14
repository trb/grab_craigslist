package craigslist;

import java.util.List;
import java.util.ArrayList;

public class Frontpage {
	public static final String frontpageUrl = "http://vancouver.en.craigslist.ca/apa/";
	
	protected String getHtml() {
		return "";
	}
	
	protected List<String> getUrls(String html) {
		List<String> urls = new ArrayList<String>();
		
		return urls;
	}
	
	public List<Page> getPages() {
		List<Page> pages = new ArrayList<Page>();
		
		String html = this.getHtml();
		List<String> pageUrls = this.getUrls(html);
		
		for (String url : pageUrls) {
			pages.add(new Page(url));
		}
		
		return pages;
	}
}
