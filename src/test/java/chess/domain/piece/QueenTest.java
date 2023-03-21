package chess.domain.piece;

import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class QueenTest {

    @DisplayName("대각선 여러 칸 움직일 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"c2", "b1", "c4", "b5", "e2", "f1", "e4", "f5"})
    void canMove_Diagonal_Infinite(String position) {
        Queen queen = new Queen(WHITE);

        assertThat(queen.isValidMove(new Position("d3"), new Position(position), null)).isTrue();
    }

    @DisplayName("가로/세로 여러 칸 움직일 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"c3", "b3", "d4", "d5", "e3", "f3", "d2", "d1"})
    void canMove_HorizontalVertical_Infinite(String position) {
        Queen queen = new Queen(WHITE);

        assertThat(queen.isValidMove(new Position("d3"), new Position(position), null)).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Queen queen = new Queen(WHITE);

        assertThat(queen.isValidMove(new Position("d3"), new Position("b2"), null)).isFalse();
    }
}
