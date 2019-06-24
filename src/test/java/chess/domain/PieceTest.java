package chess.domain;

import chess.domain.moverule.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    public void 동시성_테스트() {
        Piece expected = Piece.of(Piece.Color.WHITE, Rook.getInstance());
        Piece actual = Piece.of(Piece.Color.WHITE, Rook.getInstance());
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest() {
        Piece expected = Piece.of(Piece.Color.EMPTY, Empty.getInstance());
        Piece actual = Piece.empty();
        assertEquals(expected, actual);
    }

    @Test
    public void 색깔이_같은_Piece일때_isSameColor() {
        Piece piece = Piece.of(Piece.Color.BLACK, Rook.getInstance());
        Piece other = Piece.of(Piece.Color.BLACK, Knight.getInstance());
        assertTrue(piece.isSameColor(other));
    }

    @Test
    public void 색깔이_같은_Color일때_isSameColor() {
        Piece piece = Piece.of(Piece.Color.BLACK, Rook.getInstance());
        Piece.Color color = Piece.Color.BLACK;
        assertTrue(piece.isSameColor(color));
    }

    @Test
    public void 색깔이_다른_Piece일때_isSameColor() {
        Piece piece = Piece.of(Piece.Color.BLACK, Rook.getInstance());
        Piece other = Piece.of(Piece.Color.WHITE, Knight.getInstance());
        assertFalse(piece.isSameColor(other));
    }

    @Test
    public void 색깔이_다른_Color일때_isSameColor() {
        Piece piece = Piece.of(Piece.Color.BLACK, Rook.getInstance());
        Piece.Color color = Piece.Color.WHITE;
        assertFalse(piece.isSameColor(color));
    }

    @Test
    public void isSameName() {
        Piece piece = Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM);
        assertTrue(piece.isSameName(Pawn.NAME));
    }

    @Test
    public void isEmpty() {
        Piece piece = Piece.of(Piece.Color.EMPTY, Empty.getInstance());
        assertTrue(piece.isEmpty());
    }

    @Test
    public void isKing() {
        Piece piece = Piece.of(Piece.Color.BLACK, King.getInstance());
        assertTrue(piece.isKing());
    }

    @Test
    public void isPawn() {
        Piece piece = Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM);
        assertTrue(piece.isPawn());
    }

    @Test
    public void isValidMoveTest_참일때() {
        Piece piece = Piece.of(Piece.Color.WHITE, Rook.getInstance());
        Position origin = Position.of("1", "a");
        Position target = Position.of("8", "a");
        assertTrue(piece.isValidMove(origin, target));
    }

    @Test
    public void isValidMoveTest_거짓일때() {
        Piece piece = Piece.of(Piece.Color.WHITE, Rook.getInstance());
        Position origin = Position.of("1", "a");
        Position target = Position.of("8", "h");
        assertFalse(piece.isValidMove(origin, target));
    }

    @Test
    public void isValidAttackTest_참일때() {
        Piece piece = Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM);
        Position origin = Position.of("2", "b");
        Position target = Position.of("3", "c");
        assertTrue(piece.isValidAttack(origin, target));
    }

    @Test
    public void isValidAttackTest_거짓일때() {
        Piece piece = Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM);
        Position origin = Position.of("2", "b");
        Position target = Position.of("3", "b");
        assertFalse(piece.isValidAttack(origin, target));
    }

    @Test
    public void orElseFirstPawn_FIRST_BOTTOM() {
        Piece piece = Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM);
        Piece actual = piece.orElseFirstPawn();
        Piece expected = Piece.of(Piece.Color.WHITE, Pawn.SECOND_BOTTOM);

        assertEquals(expected, actual);
    }

    @Test
    public void orElseFirstPawn_FIRST_TOP() {
        Piece piece = Piece.of(Piece.Color.WHITE, Pawn.FIRST_TOP);
        Piece actual = piece.orElseFirstPawn();
        Piece expected = Piece.of(Piece.Color.WHITE, Pawn.SECOND_TOP);

        assertEquals(expected, actual);
    }

    @Test
    public void orElseFirstPawn_SECOND_BOTTOM() {
        Piece piece = Piece.of(Piece.Color.WHITE, Pawn.SECOND_BOTTOM);
        Piece actual = piece.orElseFirstPawn();
        Piece expected = Piece.of(Piece.Color.WHITE, Pawn.SECOND_BOTTOM);

        assertEquals(expected, actual);
    }

    @Test
    public void orElseFirstPawn_SECOND_TOP() {
        Piece piece = Piece.of(Piece.Color.WHITE, Pawn.SECOND_TOP);
        Piece actual = piece.orElseFirstPawn();
        Piece expected = Piece.of(Piece.Color.WHITE, Pawn.SECOND_TOP);

        assertEquals(expected, actual);
    }

    @Test
    public void orElseFirstPawn_OTHER() {
        Piece piece = Piece.of(Piece.Color.WHITE, Rook.getInstance());
        Piece actual = piece.orElseFirstPawn();
        Piece expected = Piece.of(Piece.Color.WHITE, Rook.getInstance());

        assertEquals(expected, actual);
    }
}