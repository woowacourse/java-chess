package chess.piece;

import chess.chessgame.MovingPosition;
import chess.chessgame.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @ParameterizedTest
    @ValueSource(strings = {"d8", "d7", "d6", "d4", "d3", "d2", "d1", "a5", "b5", "c5", "e5", "f5", "g5", "h5"})
    @DisplayName("rook 기물 이동 위치 검증 - true")
    void checkPositionWhenTrue(String input) {
        Rook rook = new Rook(Color.BLACK);
        assertThat(rook.isMovable(new MovingPosition("d5", input))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"c6", "e6", "e4", "c4"})
    @DisplayName("rook 기물 이동 위치 검증 - false")
    void checkPositionWhenFalse(String input) {
        Rook rook = new Rook(Color.BLACK);
        assertThat(rook.isMovable(new MovingPosition("d5", input))).isFalse();
    }

    @Test
    @DisplayName("from과 to 사이에 룩이 이동가능한 위치 리스트 반환")
    void checkMiddlePosition() {
        Rook rook = new Rook(Color.WHITE);
        assertThat(rook.computeMiddlePosition(new MovingPosition("b5", "f5"))).isEqualTo(
                List.of(
                        new Position(3, 2),
                        new Position(3, 3),
                        new Position(3, 4)
                )
        );
    }
}
