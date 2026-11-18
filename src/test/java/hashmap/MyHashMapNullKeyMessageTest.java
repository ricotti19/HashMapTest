package hashmap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyHashMapNullKeyMessageTest {

    private static final String MESSAGE = "null keys are not supported";

    @Test
    void putNullKeyFailsWithOurMessage() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        NullPointerException e = assertThrows(NullPointerException.class, () -> map.put(null, 1));
        assertEquals(MESSAGE, e.getMessage());
    }

    @Test
    void getNullKeyFailsWithOurMessage() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        NullPointerException e = assertThrows(NullPointerException.class, () -> map.get(null));
        assertEquals(MESSAGE, e.getMessage());
    }

    @Test
    void containsKeyNullKeyFailsWithOurMessage() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        NullPointerException e = assertThrows(NullPointerException.class, () -> map.containsKey(null));
        assertEquals(MESSAGE, e.getMessage());
    }

    @Test
    void removeNullKeyFailsWithOurMessage() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        NullPointerException e = assertThrows(NullPointerException.class, () -> map.remove(null));
        assertEquals(MESSAGE, e.getMessage());
    }
}