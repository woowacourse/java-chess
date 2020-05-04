package study;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HashMapTest {
    @Test
    void get() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1");
        assertThat(map.get(1)).isEqualTo("1");
    }

    @Test
    void equals() {

    }
}
