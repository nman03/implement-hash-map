import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K, V> implements MyMap<K, V> {
	private final static int DEFAULT_CAPACITY = 4;
	private final static int MAX_CAPACITY = 1 << 30;
	private final static float DEFAULT_LOAD_FACTOR = 0.5f;
	private int capacity;
	private float threshold;
	private int size = 0;
	private Entry<K, V>[] table;

	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public MyHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity, float threshold) {
		if (initialCapacity > MAX_CAPACITY) {
			this.capacity = MAX_CAPACITY;
		}
		else {
			this.capacity = trimCapacity(initialCapacity);
		}

		this.threshold = threshold;
		table = new Entry[capacity];
	}

	@Override
	public void clear() {
		size = 0;
		for (int i = 0 ; i < capacity ; i++) {
			if (table[i] != null) {
				table[i] = null;
			}
		}
	}

	@Override
	public boolean containsKey(K key) {
		if (get(key) != null)
			return true;
		else
			return false;
	}	

	@Override
	public boolean containsValue(V value) {
		for (int i = 0 ; i  < capacity ; i++) {
			if (table[i] != null && table[i].getValue().equals(value)) {
				return true;
			}
		}

		return false;
	}	

	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> set = new HashSet<>();

		for (int i = 0 ; i  < capacity ; i++) {
			if (table[i] != null) {
				set.add(table[i]); 
			}
		}

		return set;
	}

	@Override
	public V get(K key) {
		int index = hash(key);
		
		if (table[index] != null) {
			Entry<K, V> temp = table[index];
			if (temp.getKey().equals(key))
				return temp.getValue();
		}

		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<K>();

		for (int i = 0 ; i  < capacity ; i++) {
			if (table[i] != null) {
				set.add(table[i].getKey()); 
			}
		}	

		return set;
	}

	@Override
	public V put(K key, V value) {
		if (containsKey(key)) {
			int index = hash(key);

			if (table[index].getKey().equals(key)) {
				V oldValue = table[index].getValue();
				table[index].value = value;

				return oldValue;
			}
		}

		if (size >= capacity * threshold) {
			if (capacity == MAX_CAPACITY) {
				throw new RuntimeException("Exceeding maximum capacity");
			}

			rehash();
		}

		int index = hash(key);

		table[index] = new Entry<>(key, value);
		size++;

		return value;
	}

	@Override
	public void remove(K key) {
		int index = hash(key);

		if (table[index] != null) {
			table[index] = null;
			size--;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Set<V> values() {
		Set<V> set = new HashSet<>();

		for (int i = 0 ; i < capacity ; i++) {
			if (table[i] != null) {
				set.add(table[i].getValue()); 
			}
		}

		return set;
	}

	private int trimCapacity(int initialCapacity) {
		int capacity = 1;
		while (capacity < initialCapacity) {
			capacity <<= 1;
		}

		return capacity;
	}
	
	// Linear Probing
	private int hash(K key) {
		int j = 0;
		
		while (table[(Math.abs(key.hashCode()) + j) % capacity] != null && !table[(Math.abs(key.hashCode()) + j) % capacity].key.equals(key))
				j++;
			
		return ((Math.abs(key.hashCode()) + j) % capacity);
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		Set<Entry<K, V>> set = entrySet();
		capacity <<= 1;
		table = new Entry[capacity];
		size = 0;

		for (Entry<K, V> entry : set) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override 
	public String toString() {
		StringBuilder builder = new StringBuilder("[");

		for (int i = 0; i < capacity; i++) {
			if (table[i] != null)
				builder.append(table[i]);
		}

		builder.append("]");
		return builder.toString();
	}
}
