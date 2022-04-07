package chess.piece;

import chess.chessboard.Board;
import chess.chessboard.position.File;
import chess.chessboard.position.Rank;
import chess.game.Player;
import chess.chessboard.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }

    @DisplayName("target 위치로 움직일 수 없으면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"SEVEN,G", "SEVEN,A", "ONE,A", "ONE,G"})
    void canMove_false(final Rank rank, final File file) {
        final Map<Position, Piece> chessBoard = board.getBoard();
        final Piece bishop = new Bishop(Player.BLACK, Symbol.BISHOP);
        boolean actual = bishop.canMove(Position.of(Rank.FOUR, File.D), Position.of(rank, file), chessBoard);

        assertThat(actual).isFalse();
    }

    @DisplayName("target 위치로 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"SIX,F", "SIX,B", "TWO,B", "TWO,B"})
    void canMove_true(final Rank rank, final File file) {
        final Map<Position, Piece> chessBoard = board.getBoard();
        final Piece bishop = new Bishop(Player.BLACK, Symbol.BISHOP);
        boolean actual = bishop.canMove(Position.of(Rank.FOUR, File.D), Position.of(rank, file), chessBoard);

        assertThat(actual).isTrue();
    }
}