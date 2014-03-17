package craigslist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureExtractor {
	public static Map<String, Features> getFeatures(List<Page> pages) {
		Map<String, Features> features = new HashMap<String, Features>();
		
		for (Page page : pages) {
			features.put(page.getUrl(), page.getFeatures());
		}
		
		return features;
	}
}
