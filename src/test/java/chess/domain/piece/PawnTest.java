package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PawnTest {

    @Test
    void isMovable_메서드로_checkDirection_에러발생_테스트() {
        //given, when
        Pawn whitePawn = new Pawn(Color.WHITE);
        Position whitePawnStart = new Position("b", "2");
        Position whitePawnEnd = new Position("b", "1");

        Pawn blackPawn = new Pawn(Color.BLACK);
        Position blackPawnStart = new Position("b", "7");
        Position blackPawnEnd = new Position("b", "8");
        Color whitePawndestinationColor = Color.BLACK;
        Color blackPawndestinationColor = Color.WHITE;

        //then
        assertThatThrownBy(() -> whitePawn.isMovable(whitePawnStart, whitePawnEnd, whitePawndestinationColor))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackPawn.isMovable(blackPawnStart, blackPawnEnd, blackPawndestinationColor))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isMovable_메서드로_checkDistance_에러발생_테스트() {
        //given, when
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = new Position("b", "2");
        Position end = new Position("b", "5");
        Color destinationColor = Color.BLACK;

        //then
        assertThatThrownBy(() -> pawn.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isMovable_메서드로_첫번째_이동시_2칸_이동_가능_테스트() {
        //given, when
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = new Position("b", "2");
        Position end = new Position("b", "4");
        Color destinationColor = Color.NONE;

        //then
        assertDoesNotThrow(() -> pawn.isMovable(start, end, destinationColor));
    }

    @Test
    void isMovable_메서드로_두번째_이동부터_1칸_이동_가능_테스트() {
        //given
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = new Position("b", "2");
        Position end = new Position("b", "4");
        Position afterMoveEnd = new Position("b", "3");
        Color destinationColor = Color.NONE;

        //when
        pawn.afterFirstMove();

        //then
        assertThatThrownBy(() -> pawn.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);

        assertDoesNotThrow(() -> pawn.isMovable(start, afterMoveEnd, destinationColor));
    }

    @Test
    void isMovable_메서드로_moveForward_에러발생_테스트() {
        //given, when
        Pawn whitePawn = new Pawn(Color.WHITE);
        Pawn blackPawn = new Pawn(Color.BLACK);
        Position start = new Position("b", "2");
        Position end = new Position("b", "3");
        Color destinationColor = Color.WHITE;

        //then
        assertThatThrownBy(() -> whitePawn.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackPawn.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isMovable_메서드로_moveDiagonal_에러발생_테스트() {
        //given, when
        Pawn whitePawn = new Pawn(Color.WHITE);
        Pawn blackPawn = new Pawn(Color.BLACK);
        Position start = new Position("b", "2");
        Position end = new Position("a", "3");
        Color destinationColor = Color.NONE;

        //then
        assertThatThrownBy(() -> whitePawn.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackPawn.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> whitePawn.isMovable(start, end, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackPawn.isMovable(start, end, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isMovable_올바른_반환값_테스트() {
        //given, when
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = new Position("b", "2");
        Position end = new Position("a", "3");
        Color destinationColor = Color.BLACK;

        //then
        assertDoesNotThrow(() -> pawn.isMovable(start, end, destinationColor));
    }
}
