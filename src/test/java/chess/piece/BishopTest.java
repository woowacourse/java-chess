package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.Set;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    void 비숍이_이동할_수_있는_위치를_계산한다() {
        Board board = new Board();
        board.put(new Knight(new Position(Row.SIX, Column.B), board, Color.WHITE));
        board.put(new Knight(new Position(Row.TWO, Column.F), board, Color.BLACK));
        Piece rook = new Bishop(new Position(Row.FOUR, Column.D), board, Color.WHITE);

        Set<Position> positions = rook.getMovablePositions();

        assertThat(positions).hasSize(10);
    }
}
