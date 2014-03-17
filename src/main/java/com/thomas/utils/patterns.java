package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class patterns {
	public static Matcher match(String pattern, String data) {
		Pattern urlPattern = Pattern.compile(pattern, Pattern.DOTALL);
		return urlPattern.matcher(data);
	}
	
	public static Boolean hasAtLeastOneMatch(String pattern, String data) {
		Matcher matcher = match(pattern, data);
		
		return matcher.find();
	}
	
	public static String extract(String pattern, String data) {
		Matcher matcher = match(pattern, data);
		
		if (!matcher.find()) {
			return null;
		}
		
		return matcher.group(1);
	}
}
