package chess.model.piece;

import static chess.model.Team.WHITE;
import static chess.model.position.File.B;
import static chess.model.position.File.D;
import static chess.model.position.Rank.EIGHT;
import static chess.model.position.Rank.FIVE;
import static chess.model.position.Rank.FOUR;
import static chess.model.position.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.board.Board;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @DisplayName("초기 위치에서 target 위치로 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_false() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece knight = board.get(Position.of(EIGHT, B));
        boolean actual = knight.canMove(Position.of(EIGHT, B), Position.of(SIX, B), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("target 위치로 움직일 수 없으면 false를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"TWO,C", "TWO,E"})
    void canMove_false_2(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece knight = new Knight(WHITE);
        boolean actual = knight.canMove(Position.of(FOUR, D), Position.of(rank, file), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("초기 위치에서 target 위치로 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SIX,C", "SIX,A"})
    void canMove_true(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece knight = board.get(Position.of(EIGHT, B));
        boolean actual = knight.canMove(Position.of(EIGHT, B), Position.of(rank, file), board);

        assertThat(actual).isTrue();
    }

    @DisplayName("target 위치로 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"THREE,C", "THREE,A", "FOUR, D", "SIX,D"})
    void canMove_true_2(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece knight = new Knight(WHITE);
        boolean actual = knight.canMove(Position.of(FIVE, B), Position.of(rank, file), board);

        assertThat(actual).isTrue();
    }
}
