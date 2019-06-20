package chess.domain.RuleImpl;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {
    @Test
    public void FIRST_BOTTOM_Pawn_가능() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "h");
        Position target1 = Position.of("3", "h");
        Position target2 = Position.of("4", "h");

        assertTrue(pawn.isValidMove(origin, target1));
        assertTrue(pawn.isValidMove(origin, target2));
    }

    @Test
    public void FIRST_BOTTOM_Pawn_불가능() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "h");
        Position target1 = Position.of("1", "h");
        Position target2 = Position.of("5", "h");

        assertFalse(pawn.isValidMove(origin, target1));
        assertFalse(pawn.isValidMove(origin, target2));
    }

    @Test
    public void FIRST_TOP_Pawn_가능() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "a");
        Position target1 = Position.of("6", "a");
        Position target2 = Position.of("5", "a");

        assertTrue(pawn.isValidMove(origin, target1));
        assertTrue(pawn.isValidMove(origin, target2));
    }

    @Test
    public void FIRST_TOP_Pawn_불가능() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "a");
        Position target1 = Position.of("8", "a");
        Position target2 = Position.of("4", "a");

        assertFalse(pawn.isValidMove(origin, target1));
        assertFalse(pawn.isValidMove(origin, target2));
    }

    @Test
    public void SECOND_TOP_Pawn_가능() {
        Pawn pawn = Pawn.SECOND_TOP;

        Position origin = Position.of("6", "a");
        Position target1 = Position.of("5", "a");

        assertTrue(pawn.isValidMove(origin, target1));
    }

    @Test
    public void SECOND_TOP_Pawn_불가능() {
        Pawn pawn = Pawn.SECOND_TOP;

        Position origin = Position.of("6", "a");
        Position target1 = Position.of("4", "a");

        assertFalse(pawn.isValidMove(origin, target1));
    }

    @Test
    public void SECOND_BOTTOM_Pawn_가능() {
        Pawn pawn = Pawn.SECOND_BOTTOM;

        Position origin = Position.of("3", "c");
        Position target1 = Position.of("4", "c");

        assertTrue(pawn.isValidMove(origin, target1));
    }

    @Test
    public void SECOND_BOTTOM_Pawn_불가능() {
        Pawn pawn = Pawn.SECOND_BOTTOM;

        Position origin = Position.of("3", "c");
        Position target1 = Position.of("5", "c");
        Position target2 = Position.of("2", "c");


        assertFalse(pawn.isValidMove(origin, target1));
        assertFalse(pawn.isValidMove(origin, target2));
    }

    @Test
    public void FIRST_BOTTOM_Pawn_공격_가능() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target1 = Position.of("3", "h");
        Position target2 = Position.of("3", "f");

        assertTrue(pawn.isValidAttack(origin, target1));
        assertTrue(pawn.isValidAttack(origin, target2));
    }

    @Test
    public void FIRST_BOTTOM_Pawn_공격_불가능() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target1 = Position.of("1", "f");
        Position target2 = Position.of("1", "h");

        assertFalse(pawn.isValidAttack(origin, target1));
        assertFalse(pawn.isValidAttack(origin, target2));
    }

    @Test
    public void FIRST_TOP_Pawn_공격_가능() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "b");
        Position target1 = Position.of("6", "a");
        Position target2 = Position.of("6", "c");

        assertTrue(pawn.isValidAttack(origin, target1));
        assertTrue(pawn.isValidAttack(origin, target2));
    }

    @Test
    public void FIRST_TOP_Pawn_공격_불가능() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "b");
        Position target1 = Position.of("8", "a");
        Position target2 = Position.of("8", "c");

        assertFalse(pawn.isValidAttack(origin, target1));
        assertFalse(pawn.isValidAttack(origin, target2));
    }

    @Test
    public void SECOND_BOTTOM_Pawn_공격_가능() {
        Pawn pawn = Pawn.SECOND_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target1 = Position.of("3", "h");
        Position target2 = Position.of("3", "f");

        assertTrue(pawn.isValidAttack(origin, target1));
        assertTrue(pawn.isValidAttack(origin, target2));
    }

    @Test
    public void SECOND_BOTTOM_Pawn_공격_불가능() {
        Pawn pawn = Pawn.SECOND_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target1 = Position.of("1", "f");
        Position target2 = Position.of("1", "h");

        assertFalse(pawn.isValidAttack(origin, target1));
        assertFalse(pawn.isValidAttack(origin, target2));
    }

    @Test
    public void SECOND_TOP_Pawn_공격_가능() {
        Pawn pawn = Pawn.SECOND_TOP;

        Position origin = Position.of("7", "b");
        Position target1 = Position.of("6", "a");
        Position target2 = Position.of("6", "c");

        assertTrue(pawn.isValidAttack(origin, target1));
        assertTrue(pawn.isValidAttack(origin, target2));
    }

    @Test
    public void SECOND_TOP_Pawn_공격_불가능() {
        Pawn pawn = Pawn.SECOND_TOP;

        Position origin = Position.of("7", "b");
        Position target1 = Position.of("8", "a");
        Position target2 = Position.of("8", "c");

        assertFalse(pawn.isValidAttack(origin, target1));
        assertFalse(pawn.isValidAttack(origin, target2));
    }
}