package iiox.jim;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
	private static String URL = "http://textfiles.com/100/captmidn.txt";

	public static void main(String[] args) {
		new App().run();
	}

	public void run() {
		String text = retrieveText();
		HashMap<String, Integer> wordMap = extractWords(text);
		ArrayList<Map.Entry<String, Integer>> sortedWords = sortWords(wordMap);
		printTopTen(sortedWords);
	}
	
	//@VisibleForTesting
	public String retrieveText() {
		InputStream in = null;
		try {
			URL url = new URL(URL);
			in = url.openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertStreamToString(in);
	}

	//@VisibleForTesting
	public String convertStreamToString(InputStream is) {
		Scanner s = new Scanner(is);
		try {
			s.useDelimiter("\\A");
			return s.next();
		} finally {
			s.close();
		}
	}

	//@VisibleForTesting
	public HashMap<String, Integer> extractWords(String text) {
		HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
		Pattern p = Pattern.compile("[\\w']+");
		Matcher m = p.matcher(text);

		while (m.find()) {
			String word = text.substring(m.start(), m.end());
			if (wordMap.containsKey(word)) {
				int i = wordMap.get(word) + 1;
				wordMap.replace(word, i);
			} else {
				wordMap.put(word, 1);
			}
		}
		return wordMap;
	}

	//@VisibleForTesting
	public ArrayList<Map.Entry<String, Integer>> sortWords(HashMap<String, Integer> wordMap) {
		ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(wordMap.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
				return b.getValue().compareTo(a.getValue());
			}
		});
		return entries;
	}

	//@VisibleForTesting
	public void printTopTen(ArrayList<Map.Entry<String, Integer>> sortedWords) {
		System.out.println("De tio flest förekommande orden i fallande ordning:\n");
		for (int i = 0; i < 10; i++) {
			Map.Entry<String, Integer> map = sortedWords.get(i);
			String key = map.getKey();
			Integer value = map.getValue();
			System.out.println("Ord " + (i + 1) + " är: \"" + key + "\" och ordet förekommer " + value + " gånger.");
		}
	}
}
