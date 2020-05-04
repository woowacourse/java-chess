package chess.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SmallLetterAsciiConverterTest {

    @Test
    void convert() {
        int output = SmallLetterAsciiConverter.convert("a");
        assertThat(output).isEqualTo(1);

        output = SmallLetterAsciiConverter.convert("h");
        assertThat(output).isEqualTo(8);
    }
}