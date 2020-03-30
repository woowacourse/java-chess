package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XPositionTest {
    @Test
    void ofTest() {
        String input = "b";
        assertThat(XPosition.of(input)).isEqualTo(XPosition.B);
    }

}