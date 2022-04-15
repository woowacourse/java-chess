package chess.piece;

import chess.chessboard.Board;
import chess.chessboard.position.File;
import chess.chessboard.position.Rank;
import chess.game.Player;
import chess.chessboard.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }

    @DisplayName("한 칸 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @EnumSource(File.class)
    void canMove_one_true(File file) {
        final Map<Position, Piece> chessBoard = board.getBoard();
        final Piece pawn = chessBoard.get(Position.of(Rank.SEVEN, file));
        final boolean actual = pawn.canMove(Position.of(Rank.SEVEN, file), Position.of(Rank.SIX, file), chessBoard);

        assertThat(actual).isTrue();
    }

    @DisplayName("한 칸 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_one_false() {
        final Map<Position, Piece> chessBoard = board.getBoard();
        final Piece pawn = new Pawn(Player.BLACK, Symbol.PAWN);
        final boolean actual = pawn.canMove(Position.of(Rank.THREE, File.E), Position.of(Rank.TWO, File.E), chessBoard);

        assertThat(actual).isFalse();
    }

    @DisplayName("폰의 초기 위치에서 두 칸 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @EnumSource(File.class)
    void canMove_two_true(File file) {
        final Map<Position, Piece> chessBoard = board.getBoard();
        final Piece pawn = chessBoard.get(Position.of(Rank.SEVEN, file));
        final boolean actual = pawn.canMove(Position.of(Rank.SEVEN, file), Position.of(Rank.FIVE, file), chessBoard);

        assertThat(actual).isTrue();
    }

    @DisplayName("폰의 초기 위치에서 두 칸 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_two_false() {
        final Map<Position, Piece> chessBoard = board.getBoard();
        final Piece pawn = new Pawn(Player.BLACK, Symbol.PAWN);
        final boolean actual = pawn.canMove(Position.of(Rank.SIX, File.E), Position.of(Rank.FOUR, File.E), chessBoard);

        assertThat(actual).isFalse();
    }

    @DisplayName("대각선 앞쪽에 상대편 기물이 있을 때 움직일 수 있으면 true를 반환한다.")
    @Test
    void canMove_side_true() {
        final Map<Position, Piece> chessBoard = board.getBoard();
        final Piece pawn = new Pawn(Player.BLACK, Symbol.PAWN);
        final boolean actual = pawn.canMove(Position.of(Rank.THREE, File.E), Position.of(Rank.TWO, File.F), chessBoard);

        assertThat(actual).isTrue();
    }

    @DisplayName("대각선 앞쪽에 상대편 기물이 있을 때 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_side_false() {
        final Map<Position, Piece> chessBoard = board.getBoard();
        final Piece pawn = new Pawn(Player.BLACK, Symbol.PAWN);
        final boolean actual = pawn.canMove(Position.of(Rank.FIVE, File.E), Position.of(Rank.FOUR, File.F), chessBoard);

        assertThat(actual).isFalse();
    }
}