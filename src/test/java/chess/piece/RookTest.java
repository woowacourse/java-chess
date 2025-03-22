package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.Set;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    void 룩이_이동할_수_있는_위치를_계산한다() {
        Board board = new Board();
        board.put(new Knight(new Position(Row.FIVE, Column.G), board, Color.WHITE));
        board.put(new Knight(new Position(Row.FIVE, Column.B), board, Color.BLACK));
        Piece rook = new Rook(new Position(Row.FIVE, Column.D), board, Color.WHITE);

        Set<Position> positions = rook.getMovablePositions();

        assertThat(positions).hasSize(11);
    }
}
