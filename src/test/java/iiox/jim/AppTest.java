package iiox.jim;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class AppTest {
	RunApp app;
	ArrayList<Map.Entry<String, Integer>> entriesTest;
	ArrayList<Map.Entry<String, Integer>> entriesTest2;

	@Before
	public void init() {
		app = new RunApp();
		createTestList();
		createTestList2();
	}

	@Test
	public void retrieve_string_resource_from_web() throws Exception {
		app.retrieveText();
		InputStream anyInputStream = new ByteArrayInputStream("test data".getBytes());
		URL url = PowerMockito.mock(URL.class);
        PowerMockito.whenNew(URL.class).withParameterTypes(String.class).withArguments(Mockito.anyString()).thenReturn(url);
        Mockito.when(url.openStream()).thenReturn(anyInputStream);
        Assert.assertEquals(app.convertStreamToString(anyInputStream), "test data");
	}
	
	@Test
	public void stream_should_return_equal_context_as_string() throws Exception {
		InputStream anyInputStream = new ByteArrayInputStream("test data".getBytes());
		Assert.assertEquals(app.convertStreamToString(anyInputStream), "test data");
	}
	
	@Test
	public void verify_method_output_matches_test_data() throws Exception {
		String text = "apple apple apple orange orange banana";
		HashMap<String, Integer> testMap = new HashMap<String, Integer>();
		testMap.put("apple", 3);
		testMap.put("orange", 2);
		testMap.put("banana", 1);
		Assert.assertEquals(app.extractWords(text), testMap);
	}
	
	@Test
	public void verify_method_output_matches_test_data_2() throws Exception {
		HashMap<String, Integer> testMap = new HashMap<String, Integer>();
		testMap.put("banana", 1);
		testMap.put("orange", 2);
		testMap.put("apple", 3);
		Assert.assertEquals(app.sortWords(testMap), entriesTest);
	}
	
	@Test
	public void print_test_list() throws Exception {
		RunApp appTest = Mockito.spy(RunApp.class);
		appTest.printTopTen(entriesTest2);
		Mockito.verify(appTest).printTopTen(entriesTest2);
	}
	
	private void createTestList() {
		entriesTest = new ArrayList<Entry<String, Integer>>();
		Map.Entry<String,Integer> entry1 = new AbstractMap.SimpleEntry<String, Integer>("apple", 3);
		Map.Entry<String,Integer> entry2 = new AbstractMap.SimpleEntry<String, Integer>("orange", 2);
		Map.Entry<String,Integer> entry3 = new AbstractMap.SimpleEntry<String, Integer>("banana", 1);
		entriesTest.add(entry1);
		entriesTest.add(entry2);
		entriesTest.add(entry3);
	}
	
	private void createTestList2() {
		entriesTest2 = new ArrayList<Entry<String, Integer>>();
		Map.Entry<String,Integer> entry1 = new AbstractMap.SimpleEntry<String, Integer>("apple", 3);
		Map.Entry<String,Integer> entry2 = new AbstractMap.SimpleEntry<String, Integer>("orange", 2);
		Map.Entry<String,Integer> entry3 = new AbstractMap.SimpleEntry<String, Integer>("banana", 1);
		Map.Entry<String,Integer> entry4 = new AbstractMap.SimpleEntry<String, Integer>("pineapple", 1);
		Map.Entry<String,Integer> entry5 = new AbstractMap.SimpleEntry<String, Integer>("kiwi", 1);
		Map.Entry<String,Integer> entry6 = new AbstractMap.SimpleEntry<String, Integer>("lemon", 1);
		Map.Entry<String,Integer> entry7 = new AbstractMap.SimpleEntry<String, Integer>("physalis", 1);
		Map.Entry<String,Integer> entry8 = new AbstractMap.SimpleEntry<String, Integer>("strawberry", 1);
		Map.Entry<String,Integer> entry9 = new AbstractMap.SimpleEntry<String, Integer>("raspberry", 1);
		Map.Entry<String,Integer> entry10 = new AbstractMap.SimpleEntry<String, Integer>("blueberry", 1);
		entriesTest2.add(entry1);
		entriesTest2.add(entry2);
		entriesTest2.add(entry3);
		entriesTest2.add(entry4);
		entriesTest2.add(entry5);
		entriesTest2.add(entry6);
		entriesTest2.add(entry7);
		entriesTest2.add(entry8);
		entriesTest2.add(entry9);
		entriesTest2.add(entry10);
	}
}
