package chess.domain.piece;

import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Move;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KingTest {

    @DisplayName("대각선 한 칸 움직일 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"c2", "c4", "e2", "e4"})
    void canMove_Diagonal_Once(String position) {
        King king = new King(WHITE);
        Move move = new Move(new Position("d3"), new Position(position));

        assertThat(king.isValidMove(move, null)).isTrue();
    }

    @DisplayName("가로/세로 한 칸 움직일 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"c3", "d4", "e3", "d2"})
    void canMove_HorizontalVertical_Once(String position) {
        King king = new King(WHITE);
        Move move = new Move(new Position("d3"), new Position(position));

        assertThat(king.isValidMove(move, null)).isTrue();
    }

    @DisplayName("대각선 여러 칸 움직일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"b1", "b5", "f1", "f5"})
    void canNotMove_Diagonal_Twice(String position) {
        King king = new King(WHITE);
        Move move = new Move(new Position("d3"), new Position(position));

        assertThat(king.isValidMove(move, null)).isFalse();
    }

    @DisplayName("가로/세로 여러 칸 움직일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"b3", "d5", "f3", "d1"})
    void canNotMove_HorizontalVertical_Twice(String position) {
        King king = new King(WHITE);
        Move move = new Move(new Position("d3"), new Position(position));

        assertThat(king.isValidMove(move, null)).isFalse();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        King king = new King(WHITE);
        Move move = new Move(new Position("d3"), new Position("b2"));

        assertThat(king.isValidMove(move, null)).isFalse();
    }
}
