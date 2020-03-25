package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {
    @Test
    void ofTest() {
        String input = "b";
        assertThat(File.of(input)).isEqualTo(File.B);
    }

}