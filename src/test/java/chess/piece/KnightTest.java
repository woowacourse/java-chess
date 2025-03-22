package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.Set;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    void 나이트가_이동할_수_있는_위치를_계산한다() {
        Board board = new Board();
        board.put(new Knight(new Position(Row.SEVEN, Column.C), board, Color.WHITE));
        board.put(new Knight(new Position(Row.SEVEN, Column.E), board, Color.WHITE));
        board.put(new Knight(new Position(Row.FOUR, Column.B), board, Color.BLACK));
        Piece knight = new Knight(new Position(Row.FIVE, Column.D), board, Color.WHITE);

        Set<Position> positions = knight.getMovablePositions();

        assertThat(positions).hasSize(6);
    }

    @Test
    void 나이트가_앞으로_이동할_수_없는_위치에_있는_경우_이동할_수_있는_위치를_계산한다() {
        Board board = new Board();
        board.put(new Knight(new Position(Row.SIX, Column.B), board, Color.WHITE));
        board.put(new Knight(new Position(Row.SIX, Column.B), board, Color.BLACK));
        Piece knight = new Knight(new Position(Row.SEVEN, Column.D), board, Color.WHITE);

        Set<Position> positions = knight.getMovablePositions();

        assertThat(positions).hasSize(5);
    }
}
