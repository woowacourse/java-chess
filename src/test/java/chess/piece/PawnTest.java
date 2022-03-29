package chess.piece;

import chess.*;
import chess.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @DisplayName("한 칸 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @EnumSource(File.class)
    void canMove_one_true(File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = board.get(Position.of(Rank.SEVEN, file));
        boolean actual = pawn.canMove(Position.of(Rank.SEVEN, file), Position.of(Rank.SIX, file), board);

        assertThat(actual).isTrue();
    }

    @DisplayName("한 칸 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_one_false() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = new Pawn(Player.BLACK, "p");
        boolean actual = pawn.canMove(Position.of(Rank.THREE, File.E), Position.of(Rank.TWO, File.E), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("두 칸 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @EnumSource(File.class)
    void canMove_two_true(File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = board.get(Position.of(Rank.SEVEN, file));
        boolean actual = pawn.canMove(Position.of(Rank.SEVEN, file), Position.of(Rank.FIVE, file), board);

        assertThat(actual).isTrue();
    }

    @DisplayName("두 칸 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_two_false() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = new Pawn(Player.BLACK, "p");
        boolean actual = pawn.canMove(Position.of(Rank.SIX, File.E), Position.of(Rank.FOUR, File.E), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("대각선 앞쪽으로 움직일 수 있으면 true를 반환한다.")
    @Test
    void canMove_side_true() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = new Pawn(Player.BLACK, "p");
        boolean actual = pawn.canMove(Position.of(Rank.THREE, File.E), Position.of(Rank.TWO, File.F), board);

        assertThat(actual).isTrue();
    }

    @DisplayName("대각선 앞쪽으로 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_side_false() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece pawn = new Pawn(Player.BLACK, "p");
        boolean actual = pawn.canMove(Position.of(Rank.FIVE, File.E), Position.of(Rank.FOUR, File.F), board);

        assertThat(actual).isFalse();
    }
}
