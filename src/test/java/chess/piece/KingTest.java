package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.Set;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    void 킹이_이동할_수_있는_위치를_계산한다() {
        Board board = new Board();
        board.put(new Knight(new Position(Row.SIX, Column.E), board, Color.WHITE));
        board.put(new Knight(new Position(Row.SIX, Column.F), board, Color.WHITE));
        board.put(new Knight(new Position(Row.SIX, Column.G), board, Color.BLACK));
        Piece king = new King(new Position(Row.FIVE, Column.F), board, Color.WHITE);

        Set<Position> positions = king.getMovablePositions();

        assertThat(positions).hasSize(6);
    }

    @Test
    void 킹이_뒤로_이동할_수_없는_위치에_있는_경우_이동할_수_있는_위치를_계산한다() {
        Board board = new Board();
        Piece king = new King(new Position(Row.ONE, Column.D), board, Color.WHITE);

        Set<Position> positions = king.getMovablePositions();

        assertThat(positions).hasSize(5);
    }
}
