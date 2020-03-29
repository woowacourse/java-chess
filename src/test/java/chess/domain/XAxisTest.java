package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XAxisTest {
    @Test
    void ofTest() {
        String input = "b";
        assertThat(XAxis.of(input)).isEqualTo(XAxis.B);
    }

}