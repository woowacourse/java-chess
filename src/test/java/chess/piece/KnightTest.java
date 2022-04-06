package chess.piece;

import chess.chessboard.Board;
import chess.chessboard.position.File;
import chess.chessboard.position.Rank;
import chess.chessboard.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }

    @DisplayName("target 위치로 움직일 수 없으면 false를 반환한다.")
    @Test
    void canMove_false() {
        final Map<Position, Piece> chessBoard = board.getBoard();
        final Piece knight = chessBoard.get(Position.of(Rank.EIGHT, File.B));
        final boolean actual = knight.canMove(Position.of(Rank.EIGHT, File.B), Position.of(Rank.SIX, File.B), chessBoard);

        assertThat(actual).isFalse();
    }

    @DisplayName("target 위치로 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SIX,C", "SIX,A"})
    void canMove_true(Rank rank, File file) {
        final Map<Position, Piece> chessBoard = board.getBoard();
        final Piece knight = chessBoard.get(Position.of(Rank.EIGHT, File.B));
        final boolean actual = knight.canMove(Position.of(Rank.EIGHT, File.B), Position.of(rank, file), chessBoard);

        assertThat(actual).isTrue();
    }
}