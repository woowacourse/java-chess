package chess.model.piece;

import static chess.model.Team.BLACK;
import static chess.model.position.File.D;
import static chess.model.position.Rank.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.board.Board;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    @DisplayName("target 위치로 움직일 수 없으면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"SEVEN,F", "SEVEN,C", "ONE,A", "ONE,G"})
    void canMove_false(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece bishop = new Bishop(BLACK);
        boolean actual = bishop.canMove(Position.of(FOUR, D), Position.of(rank, file), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("target 위치로 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"SIX,F", "SIX,B", "TWO,B", "TWO,B"})
    void canMove_true(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece bishop = new Bishop(BLACK);
        boolean actual = bishop.canMove(Position.of(FOUR, D), Position.of(rank, file), board);

        assertThat(actual).isTrue();
    }
}
