package chess.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AsciiConverterTest {

    @Test
    void convert() {
        int output = AsciiConverter.convert("a");
        assertThat(output).isEqualTo(1);
        output = AsciiConverter.convert("h");
        assertThat(output).isEqualTo(8);
        output = AsciiConverter.convert("1");
        assertThat(output).isEqualTo(1);
        output = AsciiConverter.convert("8");
        assertThat(output).isEqualTo(8);
    }
}