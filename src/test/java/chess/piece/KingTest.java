package chess.piece;

import static chess.File.D;
import static chess.File.E;
import static chess.Rank.EIGHT;
import static chess.Rank.FIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.File;
import chess.Player;
import chess.Position;
import chess.Rank;
import chess.board.Board;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KingTest {

    @DisplayName("target 값이 이동법위를 벗어나면 예외를 발생한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SIX,E", "EIGHT, A", "FIVE, H"})
    void canMove_exception(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece king = board.get(Position.of(EIGHT, E));
        assertThatThrownBy(() -> king.canMove_2(Position.of(EIGHT, E), Position.of(rank, file), board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 기물이 이동할수 없는 Target이 입력 됬습니다.");
    }

    @DisplayName("target 위치로 움직일 수 없으면 false를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SEVEN,E"})
    void canMove_false(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece king = board.get(Position.of(EIGHT, E));
        boolean actual = king.canMove_2(Position.of(EIGHT, E), Position.of(rank, file), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("target 위치로 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SIX,D", "FOUR,D", "FIVE,C", "FIVE,E", "SIX,E", "FOUR,E", "SIX,C", "FOUR,C"})
    void canMove_true(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece king = new King(Player.BLACK, "K");
        boolean actual = king.canMove_2(Position.of(FIVE, D), Position.of(rank, file), board);

        assertThat(actual).isTrue();
    }
}
