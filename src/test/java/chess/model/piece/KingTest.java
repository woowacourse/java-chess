package chess.model.piece;

import static chess.model.Team.BLACK;
import static chess.model.position.File.D;
import static chess.model.position.File.E;
import static chess.model.position.Rank.EIGHT;
import static chess.model.position.Rank.FIVE;
import static chess.model.position.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.board.Board;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KingTest {

    @DisplayName("초기 위치에서 target 위치로 움직일 수 없으면 false를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SEVEN,E", "EIGHT,D", "EIGHT,C", "EIGHT,F", "SEVEN,D", "SEVEN,F"})
    void canMove_false(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece king = board.get(Position.of(EIGHT, E));
        boolean actual = king.canMove(Position.of(EIGHT, E), Position.of(rank, file), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("target 위치로 움직일 수 없으면 false를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SEVEN,E", "SEVEN,D", "SEVEN,C"})
    void canMove_false_2(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece king = new King(BLACK);
        boolean actual = king.canMove(Position.of(SIX, E), Position.of(rank, file), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("target 위치로 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SIX,D", "FOUR,D", "FIVE,C", "FIVE,E", "SIX,E", "FOUR,E", "SIX,C", "FOUR,C"})
    void canMove_true(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece king = new King(BLACK);
        boolean actual = king.canMove(Position.of(FIVE, D), Position.of(rank, file), board);

        assertThat(actual).isTrue();
    }
}
