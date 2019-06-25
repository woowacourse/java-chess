package chess.domain.RuleImpl;

import chess.domain.Position;
import chess.domain.rule.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {
    @Test
    public void FIRST_BOTTOM_Pawn_한칸_이동() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "h");
        Position target = Position.of("3", "h");

        assertTrue(pawn.isValidMove(origin, target));
    }

    @Test
    public void FIRST_BOTTOM_Pawn_두칸_이동() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "h");
        Position target = Position.of("4", "h");

        assertTrue(pawn.isValidMove(origin, target));
    }

    @Test
    public void FIRST_BOTTOM_Pawn_뒤_이동_불가능() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "h");
        Position target = Position.of("1", "h");

        assertFalse(pawn.isValidMove(origin, target));
    }

    @Test
    public void FIRST_BOTTOM_Pawn_세칸_이동_불가능() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "h");
        Position target = Position.of("5", "h");

        assertFalse(pawn.isValidMove(origin, target));
    }

    @Test
    public void FIRST_TOP_Pawn__한칸_이동() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "a");
        Position target = Position.of("6", "a");

        assertTrue(pawn.isValidMove(origin, target));
    }

    @Test
    public void FIRST_TOP_Pawn__두칸_이동() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "a");
        Position target = Position.of("5", "a");

        assertTrue(pawn.isValidMove(origin, target));
    }

    @Test
    public void FIRST_TOP_Pawn_뒤로_이동_불가능() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "a");
        Position target = Position.of("8", "a");

        assertFalse(pawn.isValidMove(origin, target));
    }

    @Test
    public void FIRST_TOP_Pawn_세칸_이동_불가능() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "a");
        Position target = Position.of("4", "a");

        assertFalse(pawn.isValidMove(origin, target));
    }

    @Test
    public void SECOND_TOP_Pawn_한칸_이동() {
        Pawn pawn = Pawn.SECOND_TOP;

        Position origin = Position.of("6", "a");
        Position target = Position.of("5", "a");

        assertTrue(pawn.isValidMove(origin, target));
    }

    @Test
    public void SECOND_TOP_Pawn_두칸_이동_불가능() {
        Pawn pawn = Pawn.SECOND_TOP;

        Position origin = Position.of("6", "a");
        Position target = Position.of("4", "a");

        assertFalse(pawn.isValidMove(origin, target));
    }

    @Test
    public void SECOND_BOTTOM_Pawn_한칸_이동_가능() {
        Pawn pawn = Pawn.SECOND_BOTTOM;

        Position origin = Position.of("3", "c");
        Position target = Position.of("4", "c");

        assertTrue(pawn.isValidMove(origin, target));
    }

    @Test
    public void SECOND_BOTTOM_Pawn_두칸_이동_불가능() {
        Pawn pawn = Pawn.SECOND_BOTTOM;

        Position origin = Position.of("3", "c");
        Position target = Position.of("5", "c");

        assertFalse(pawn.isValidMove(origin, target));
    }

    @Test
    public void SECOND_BOTTOM_Pawn_뒤로_이동_불가능() {
        Pawn pawn = Pawn.SECOND_BOTTOM;

        Position origin = Position.of("3", "c");
        Position target = Position.of("2", "c");

        assertFalse(pawn.isValidMove(origin, target));
    }

    @Test
    public void FIRST_BOTTOM_Pawn_공격_왼쪽_대각선_가능() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target = Position.of("3", "f");

        assertTrue(pawn.isValidAttack(origin, target));
    }

    @Test
    public void FIRST_BOTTOM_Pawn_공격_오른쪽_대각선_가능() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target = Position.of("3", "h");

        assertTrue(pawn.isValidAttack(origin, target));
    }

    @Test
    public void FIRST_BOTTOM_Pawn_공격_뒤_오른쪽_대각선_불가능() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target = Position.of("1", "h");

        assertFalse(pawn.isValidAttack(origin, target));
    }

    @Test
    public void FIRST_BOTTOM_Pawn_공격_뒤_왼쪽_대각선_불가능() {
        Pawn pawn = Pawn.FIRST_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target = Position.of("1", "f");

        assertFalse(pawn.isValidAttack(origin, target));
    }

    @Test
    public void FIRST_TOP_Pawn_공격_왼쪽_대각선() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "b");
        Position target = Position.of("6", "a");

        assertTrue(pawn.isValidAttack(origin, target));
    }

    @Test
    public void FIRST_TOP_Pawn_공격_오른쪽_대각선() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "b");
        Position target = Position.of("6", "c");

        assertTrue(pawn.isValidAttack(origin, target));
    }

    @Test
    public void FIRST_TOP_Pawn_공격_뒤_오른쪽_불가능() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "b");
        Position target = Position.of("8", "c");

        assertFalse(pawn.isValidAttack(origin, target));
    }

    @Test
    public void FIRST_TOP_Pawn_공격_뒤_왼쪽_불가능() {
        Pawn pawn = Pawn.FIRST_TOP;

        Position origin = Position.of("7", "b");
        Position target = Position.of("8", "a");

        assertFalse(pawn.isValidAttack(origin, target));
    }

    @Test
    public void SECOND_BOTTOM_Pawn_공격_오른쪽_대각선() {
        Pawn pawn = Pawn.SECOND_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target = Position.of("3", "h");

        assertTrue(pawn.isValidAttack(origin, target));
    }

    @Test
    public void SECOND_BOTTOM_Pawn_공격_왼쪽_대각선() {
        Pawn pawn = Pawn.SECOND_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target = Position.of("3", "f");

        assertTrue(pawn.isValidAttack(origin, target));
    }

    @Test
    public void SECOND_BOTTOM_Pawn_공격_뒤_왼쪽_대각선_불가능() {
        Pawn pawn = Pawn.SECOND_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target = Position.of("1", "f");

        assertFalse(pawn.isValidAttack(origin, target));
    }

    @Test
    public void SECOND_BOTTOM_Pawn_공격_뒤_오른쪽_대각선_불가능() {
        Pawn pawn = Pawn.SECOND_BOTTOM;

        Position origin = Position.of("2", "g");
        Position target = Position.of("1", "h");

        assertFalse(pawn.isValidAttack(origin, target));
    }

    @Test
    public void SECOND_TOP_Pawn_공격_오른쪽_대각선_가능() {
        Pawn pawn = Pawn.SECOND_TOP;

        Position origin = Position.of("7", "b");
        Position target = Position.of("6", "c");

        assertTrue(pawn.isValidAttack(origin, target));
    }

    @Test
    public void SECOND_TOP_Pawn_공격_왼쪽_대각선_가능() {
        Pawn pawn = Pawn.SECOND_TOP;

        Position origin = Position.of("7", "b");
        Position target = Position.of("6", "a");

        assertTrue(pawn.isValidAttack(origin, target));
    }

    @Test
    public void SECOND_TOP_Pawn_공격_뒤_왼쪽_불가능() {
        Pawn pawn = Pawn.SECOND_TOP;

        Position origin = Position.of("7", "b");
        Position target = Position.of("8", "a");

        assertFalse(pawn.isValidAttack(origin, target));
    }

    @Test
    public void SECOND_TOP_Pawn_공격_뒤_오른쪽_불가능() {
        Pawn pawn = Pawn.SECOND_TOP;

        Position origin = Position.of("7", "b");
        Position target = Position.of("8", "c");

        assertFalse(pawn.isValidAttack(origin, target));
    }
}