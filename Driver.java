public class Driver {
	public static void main(String[] args) {
		// Test 2 <String, Integer>
		System.out.println("Map Test 1:");
		MyMap<String, Integer> map = new MyHashMap<>();
		map.put("Aa", 30);
		map.put("Arizona", 31);
		map.put("Colorado", 29);
		map.put("New Jersey", 19);
		map.put("BB", 65);
		map.put("New York", 19);
		map.put("BB", 33);

		System.out.println("Size of the Map: " + map.size());
		System.out.println("Entries in Map: " + map);
		
		System.out.println("The value for Colorado is " + map.get("Colorado"));
		System.out.println("Is Arizona in the Map? " + map.containsKey("Arizona"));
		System.out.println("Is Florida in the Map? " + map.containsKey("Florida"));
		System.out.println("Is value 33 in the Map? " + map.containsValue(33));
		System.out.println("Is value 65 in the Map? " + map.containsValue(65));

		map.remove("Colorado");
		System.out.println("Entries in Map: " + map);
		System.out.println("Size of the Map: " + map.size());

		map.clear();
		System.out.println("Entries in Map: " + map); 
		System.out.println("Size of the Map: " + map.size());	
		System.out.println();

		// Test 2 <Integer, String>
		System.out.println("Map Test 2:");
		MyMap<Integer, String> map1 = new MyHashMap<>();
		map1.put(34, "Arizona");
		map1.put(54, "Colorado");
		map1.put(29, "New Jersey");
		map1.put(12, "California");
		map1.put(43, "New York");

		System.out.println("Size of the Map: " + map1.size());
		System.out.println("Entries in Map: " + map1);

		System.out.println("The value for 12 is " + map1.get(12));
		System.out.println("Is 34 in the Map? " + map1.containsKey(34));
		System.out.println("Is 25 in the Map? " + map1.containsKey(25));
		System.out.println("Is value Georgia in the Map? " + map1.containsValue("Georgia"));
		System.out.println("Is value California in the Map? " + map1.containsValue("California"));

		map1.remove(43);
		System.out.println("Entries in Map: " + map1);
		System.out.println("Size of the Map: " + map1.size());

		map1.clear();
		System.out.println("Entries in Map: " + map1); 
		System.out.println("Size of the Map: " + map1.size());		
	}
} 
