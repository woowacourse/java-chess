package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class QueenTest {

    @ParameterizedTest
    @ValueSource(strings = {"b4", "a3", "c3", "b2", "a4", "a2", "c4", "c2"})
    @DisplayName("이동 가능한 경우 true를 반환")
    void is_movable(String to) {
        Board board = Board.create();
        Queen queen = new Queen(Symbol.QUEEN, Team.BLACK);
        boolean actual = queen.isMovable(board, Coordinate.of("b3"), Coordinate.of(to));
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("이동 불가능한 경우 false를 반환")
    void is_not_movable() {
        Board board = Board.create();
        Queen queen = new Queen(Symbol.QUEEN, Team.BLACK);
        boolean actual = queen.isMovable(board, Coordinate.of("c2"), Coordinate.of("h6"));
        assertThat(actual).isFalse();
    }
}
