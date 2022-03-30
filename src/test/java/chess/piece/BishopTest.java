package chess.piece;

import chess.chessgame.MovingPosition;
import chess.chessgame.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    @ParameterizedTest
    @ValueSource(strings = {"a8", "b7", "c6", "e4", "f3", "g2", "h1", "g8", "f7", "e6", "c4", "b3", "a2"})
    @DisplayName("bishop 기물 이동 위치 검증 - true")
    void checkPositionWhenTrue(String input) {
        Bishop bishop = new Bishop(Color.BLACK);
        assertThat(bishop.isMovable(new MovingPosition("d5", input))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"d6", "e5", "d4", "c5"})
    @DisplayName("bishop 기물 이동 위치 검증 - false")
    void checkPositionWhenFalse(String input) {
        Bishop bishop = new Bishop(Color.BLACK);
        assertThat(bishop.isMovable(new MovingPosition("d5", input))).isFalse();
    }

    @Test
    @DisplayName("from과 to 사이에 비숍이 이동가능한 위치 리스트 반환")
    void checkMiddlePosition() {
        Bishop bishop = new Bishop(Color.WHITE);
        assertThat(bishop.computeMiddlePosition(new MovingPosition("f7", "b3")))
                .isEqualTo(List.of(
                        new Position(4, 2),
                        new Position(3, 3),
                        new Position(2, 4))
                );
    }
}
