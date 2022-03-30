package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    private Board board;
    private Bishop bishop;

    @BeforeEach
    void init() {
        board = Board.create();
        bishop = new Bishop(Symbol.BISHOP, Team.WHITE);
    }

    @ParameterizedTest
    @DisplayName("이동 가능한 위치인 경우 True를 반환")
    @CsvSource(value = {
            "c4,d5",
            "c4,d3",
            "c4,b5",
            "c4,b3"
    })
    void is_movable(String from, String to) {
        boolean actual = bishop.isMovable(board, Coordinate.of(from), Coordinate.of(to));
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("해당 방향을 비숍이 가지고 있지 않으면 false를 반환")
    void is_not_movable() {
        boolean actual = bishop.isMovable(board, Coordinate.of("c3"), Coordinate.of("c4"));
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("두 지점 사이에 장애물이 있는 경우 false를 반환")
    void is_not_movable2() {
        boolean actual = bishop.isMovable(board, Coordinate.of("c1"), Coordinate.of("e3"));
        assertThat(actual).isFalse();
    }
}
