package chess.piece;

import chess.chessgame.MovingPosition;
import chess.chessgame.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    @ParameterizedTest
    @ValueSource(strings = {"a8", "b7", "c6", "e4", "f3", "g2", "h1", "g8", "f7", "e6", "c4", "b3", "a2"})
    @DisplayName("queen 기물 대각선 이동 위치 검증 - true")
    void checkQueenPositionDiagonal(String input) {
        Queen queen = new Queen(Color.BLACK);
        assertThat(queen.isMovable(new MovingPosition("d5", input))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"d8", "d7", "d6", "d4", "d3", "d2", "d1", "a5", "b5", "c5", "e5", "f5", "g5", "h5"})
    @DisplayName("queen 기물 직선 이동 위치 검증 - true")
    void checkQueenPositionUpDownLeftRight(String input) {
        Queen queen = new Queen(Color.BLACK);
        assertThat(queen.isMovable(new MovingPosition("d5", input))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"b6", "c8", "e7", "f6", "g3", "e2", "b1", "a6"})
    @DisplayName("queen 기물 이동 위치 검증 - false")
    void checkBishopPositionWhenFalse(String input) {
        Queen queen = new Queen(Color.BLACK);
        assertThat(queen.isMovable(new MovingPosition("d5", input))).isFalse();
    }

    @Test
    @DisplayName("from과 to 사이에 사이에 퀸이 이동가능한 위치 리스트 반환 - 대각선")
    void checkMiddlePositionCross() {
        Queen queen = new Queen(Color.WHITE);
        assertThat(queen.computeMiddlePosition(new MovingPosition("f7", "b3")))
                .isEqualTo(List.of(
                        new Position(4, 2),
                        new Position(3, 3),
                        new Position(2, 4)));
    }

    @Test
    @DisplayName("from과 to 사이에 사이에 퀸이 이동가능한 위치 리스트 반환 - 직선")
    void checkMiddlePositionLinear() {
        Queen queen = new Queen(Color.WHITE);
        assertThat(queen.computeMiddlePosition(new MovingPosition("b5", "f5"))).isEqualTo(
                List.of(
                        new Position(3, 2),
                        new Position(3, 3),
                        new Position(3, 4)
                )
        );
    }

}
