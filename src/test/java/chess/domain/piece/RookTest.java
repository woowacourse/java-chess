package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RookTest {

    @ParameterizedTest
    @CsvSource(value = {
            "c4,c5",
            "c4,c3",
            "c4,d4",
            "c4,b4"
    })
    @DisplayName("이동 가능한 경우 true를 반환")
    void move(String from, String to) {
        Board board = Board.create();
        Rook rook = new Rook(Symbol.ROOK, Team.WHITE);
        boolean actual = rook.isMovable(board, Coordinate.of(from), Coordinate.of(to));
        assertThat(actual).isTrue();
    }
}
