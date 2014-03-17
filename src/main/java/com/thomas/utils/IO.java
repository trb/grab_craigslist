package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class IO {
	public static String inputStreamToString(InputStream inputStream,
			String encoding, Integer bufferSize) throws IOException {
		Reader inputStreamReader = new InputStreamReader(inputStream, encoding);

		char[] buffer = new char[bufferSize];
		StringBuilder assembledString = new StringBuilder();

		while (true) {
			int bytesRead = inputStreamReader.read(buffer, 0, buffer.length);

			if (bytesRead < 0) {
				break;
			}

			assembledString.append(buffer, 0, bytesRead);
		}

		return assembledString.toString();
	}
	
	public static String inputStreamToString(InputStream inputStream, String encoding) throws IOException {
		return inputStreamToString(inputStream, encoding, 4096);
	}
	
	public static String inputStreamToString(InputStream inputStream) throws IOException {
		return inputStreamToString(inputStream, "UTF-8", 4096);
	}
}
