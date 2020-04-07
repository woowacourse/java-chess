package study;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Integer {
    @Test
    void ascii() {
        int a = 'a';
        assertThat(a).isEqualTo(97);

        int z = 'z';
        assertThat(z).isEqualTo(122);
    }
}
