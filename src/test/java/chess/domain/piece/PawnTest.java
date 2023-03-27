package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PieceInfo.BLACK_PAWN_INFO;
import static chess.domain.piece.PieceInfo.WHITE_PAWN_INFO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnTest {

    @Test
    void isMovable_메서드로_checkDirection_에러발생_테스트() {
        //given, when
        Position whitePawnStart = new Position("b", "2");
        Position whitePawnEnd = new Position("b", "1");
        Pawn whitePawn = new Pawn(WHITE_PAWN_INFO, whitePawnStart.getColumn());

        Position blackPawnStart = new Position("b", "7");
        Position blackPawnEnd = new Position("b", "8");
        Pawn blackPawn = new Pawn(BLACK_PAWN_INFO, blackPawnStart.getColumn());
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
        Position start = new Position("b", "2");
        Position end = new Position("b", "5");
        Pawn pawn = new Pawn(WHITE_PAWN_INFO, start.getColumn());
        Color destinationColor = Color.BLACK;

        //then
        assertThatThrownBy(() -> pawn.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isMovable_메서드로_첫번째_이동시_2칸_이동_가능_테스트() {
        //given, when
        Position start = new Position("b", "2");
        Position end = new Position("b", "4");
        Pawn pawn = new Pawn(WHITE_PAWN_INFO, start.getColumn());
        Color destinationColor = Color.NONE;

        //then
        assertDoesNotThrow(() -> pawn.isMovable(start, end, destinationColor));
    }

    @Test
    void isMovable_메서드로_첫번째_이동시_2칸과_1칸이동_모두_가능_테스트() {
        //given, when
        Position firstStart = new Position("b", "2");
        Position end = new Position("b", "4");
        Position afterMoveEnd = new Position("b", "3");
        Pawn pawn = new Pawn(WHITE_PAWN_INFO, firstStart.getColumn());
        Color destinationColor = Color.NONE;

        //then
        assertDoesNotThrow(() -> pawn.isMovable(firstStart, end, destinationColor));
        assertDoesNotThrow(() -> pawn.isMovable(firstStart, afterMoveEnd, destinationColor));
    }

    @Test
    void isMovable_메서드로_moveForward_에러발생_테스트() {
        //given, when
        Position start = new Position("b", "2");
        Position end = new Position("b", "3");
        Pawn whitePawn = new Pawn(WHITE_PAWN_INFO, start.getColumn());
        Pawn blackPawn = new Pawn(BLACK_PAWN_INFO, start.getColumn());
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
        Position start = new Position("b", "2");
        Position end = new Position("a", "3");
        Pawn whitePawn = new Pawn(WHITE_PAWN_INFO, start.getColumn());
        Pawn blackPawn = new Pawn(BLACK_PAWN_INFO, start.getColumn());
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
        Position start = new Position("b", "2");
        Pawn pawn = new Pawn(WHITE_PAWN_INFO, start.getColumn());
        Position end = new Position("a", "3");
        Color destinationColor = Color.BLACK;

        //then
        assertDoesNotThrow(() -> pawn.isMovable(start, end, destinationColor));
    }
}
