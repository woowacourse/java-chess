package study;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringTest {
    @Test
    void substring() {
        String string = "abc";
        assertThat(string.substring(0,1)).isEqualTo("a");

    }

}
