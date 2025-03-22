package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.Set;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    void 퀸이_이동할_수_있는_위치를_계산한다() {
        Board board = new Board();
        board.put(new Knight(new Position(Row.SIX, Column.B), board, Color.WHITE));
        board.put(new Knight(new Position(Row.TWO, Column.F), board, Color.BLACK));
        board.put(new Knight(new Position(Row.FOUR, Column.G), board, Color.WHITE));
        board.put(new Knight(new Position(Row.FOUR, Column.B), board, Color.BLACK));
        Piece rook = new Queen(new Position(Row.FOUR, Column.D), board, Color.WHITE);

        Set<Position> positions = rook.getMovablePositions();

        assertThat(positions).hasSize(21);
    }
}
