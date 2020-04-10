package chess.domain;

import chess.domain.piece.position.XPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XPositionTest {
    @DisplayName("of 테스트")
    @Test
    void ofTest() {
        String input = "b";
        assertThat(XPosition.of(input)).isEqualTo(XPosition.B);
    }
}
