package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    void 폰이_처음_이동할_수_있는_위치를_계산한다() {
        Board board = new Board();
        Piece pawn = new Pawn(new Position(Row.SEVEN, Column.G), board, Color.BLACK);

        Set<Position> positions = pawn.getMovablePositions();

        assertThat(positions).hasSize(2);
    }

    @Test
    void 폰의_이동이_처음이_아니면_한_칸만_이동할_수_있다() {
        Board board = new Board();
        Piece pawn = new Pawn(new Position(Row.SEVEN, Column.G), board, Color.BLACK);
        pawn.move(new Position(Row.SIX, Column.G));

        Set<Position> positions = pawn.getMovablePositions();

        assertThat(positions).hasSize(1);
    }

    @Test
    void 폰_앞에_우리팀_기물이_있으면_두_칸을_움직일_수_없다() {
        Board board = new Board();
        board.put(new Knight(new Position(Row.SIX, Column.G), board, Color.WHITE));
        Piece pawn = new Pawn(new Position(Row.SEVEN, Column.G), board, Color.BLACK);

        Set<Position> positions = pawn.getMovablePositions();

        assertThat(positions).hasSize(1);
    }

    @Test
    void 폰의_대각선에_상대_기물이_있으면_이동할_수_있다() {
        Board board = new Board();
        board.put(new Knight(new Position(Row.FOUR, Column.F), board, Color.WHITE));
        Piece pawn = new Pawn(new Position(Row.FIVE, Column.G), board, Color.BLACK);

        Set<Position> positions = pawn.getMovablePositions();

        assertThat(positions).hasSize(3);
    }


    @Test
    void 폰이_끝까지_가면_갈_곳이_없다() {
        Board board = new Board();
        Piece pawn = new Pawn(new Position(Row.ONE, Column.D), board, Color.BLACK);

        Set<Position> positions = pawn.getMovablePositions();

        assertThat(positions).isEmpty();
    }
}
