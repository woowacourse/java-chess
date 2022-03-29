package chess.piece;

import static chess.File.B;
import static chess.File.E;
import static chess.Rank.EIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.File;
import chess.Position;
import chess.Rank;
import chess.board.Board;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @DisplayName("target 값이 이동법위를 벗어나면 예외를 발생한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SIX,A", "SEVEN,E"})
    void canMove_exception(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece knight = board.get(Position.of(Rank.EIGHT, B));
        assertThatThrownBy(() -> knight.canMove_2(Position.of(EIGHT, B), Position.of(rank, file), board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 기물이 이동할수 없는 Target이 입력 됬습니다.");
    }

    @DisplayName("target 위치로 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_false() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece knight = board.get(Position.of(Rank.EIGHT, B));
        boolean actual = knight.canMove(Position.of(Rank.EIGHT, B), Position.of(Rank.SIX, B), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("target 위치로 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SIX,C", "SIX,A"})
    void canMove_true(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece knight = board.get(Position.of(Rank.EIGHT, B));
        boolean actual = knight.canMove(Position.of(Rank.EIGHT, B), Position.of(rank, file), board);

        assertThat(actual).isTrue();
    }
}
