package chess.piece;

import chess.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @DisplayName("target 위치로 움직일 수 있으면 false를 반환한다.")
    @Test
    void canMove_false() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece knight = board.get(new Position(Rank.EIGHT, File.B));
        Boolean actual = knight.canMove(new Position(Rank.EIGHT, File.B), new Position(Rank.SIX, File.B), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("target 위치로 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SIX,C", "SIX,A"})
    void canMove_true(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece knight = board.get(new Position(Rank.EIGHT, File.B));
        Boolean actual = knight.canMove(new Position(Rank.EIGHT, File.B), new Position(rank, file), board);

        assertThat(actual).isTrue();
    }
}