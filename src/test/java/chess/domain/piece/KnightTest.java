package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @ParameterizedTest
    @CsvSource(value = {
            "d5,c7", "d5,e7",
            "d5,f6", "d5,f4",
            "d5,e3", "d5,c3",
            "d5,b4", "d5,b6"
    })
    @DisplayName("이동 가능한 경우 True를 반환")
    void isMovable(String from, String to) {
        Board board = Board.create();
        Knight knight = new Knight(Symbol.KNIGHT, Team.WHITE);
        boolean actual = knight.isMovable(board, Coordinate.of(from), Coordinate.of(to));
        assertThat(actual).isTrue();
    }
}
