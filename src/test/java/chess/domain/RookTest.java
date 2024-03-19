package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("생성 테스트")
    @Test
    public void create() {
        assertThatCode(() -> new Rook(Color.BLACK))
                .doesNotThrowAnyException();
    }

    @DisplayName("갈 수 있는 위치들을 알 수 있다.")
    @Test
    public void movablePositions() {

    }
}
