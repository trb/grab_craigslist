package craigslist;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Frontpage {
	protected static final Integer THREAD_COUNT = 100;
	
	public static final String baseUrl = "http://vancouver.en.craigslist.ca";
	public static final String frontpageUrl = "http://vancouver.en.craigslist.ca/apa/";

	protected String getHtml() throws MalformedURLException, IOException {
		return utils.http.get(frontpageUrl);
	}

	protected List<String> getUrls(String html) {
		List<String> urls = new ArrayList<String>();
		Matcher urlMatcher = utils.patterns.match(
				"(\\/[a-zA-Z0-9]+?\\/apa\\/\\d+?\\.html)", html);

		while (urlMatcher.find()) {
			urls.add(baseUrl.concat(urlMatcher.group()));
		}

		return urls;
	}

	public List<Page> getPages() throws MalformedURLException, IOException {
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
		List<Page> pages = new ArrayList<Page>();

		String html = getHtml();
		List<String> pageUrls = getUrls(html);

		for (String url : pageUrls) {
			try {
				Page page = new Page(url);
				pages.add(page);
				executor.execute(page);
			} catch (Exception exception) {
				// If anything went wrong, just skip the page
				System.out.println(exception);
			}
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(5, TimeUnit.MINUTES);
		} catch (Exception exception) {
			System.out.print("Execution failed:\n");
			System.out.print(exception);
		}

		return pages;
	}
}
