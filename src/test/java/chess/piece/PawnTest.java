package chess.piece;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.board.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Pawn;

public class PawnTest {
    @DisplayName("Pawn 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    void create(int column) {
        Pawn blackPawn = new Pawn(BLACK, Point.valueOf(1, column));
        assertThat(PieceType.findPiece(1, column)).isEqualTo(blackPawn);
        Pawn whitePawn = new Pawn(WHITE, Point.valueOf(6, column));
        assertThat(PieceType.findPiece(6, column)).isEqualTo(whitePawn);
    }

    @DisplayName("검은 Pawn의 불가능한 방향 확인")
    @Test
    void checkBlackPawnImpossibleMove() {
        Pawn blackPawn = new Pawn(BLACK, Point.valueOf(1, 3));
        Empty empty = new Empty(NOTHING, Point.valueOf(0, 3));

        assertThatThrownBy(
            () -> blackPawn.direction(empty)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("하얀 Pawn의 불가능한 방향 확인")
    @Test
    void checkWhitePawnImpossibleMove() {
        Pawn whitePawn = new Pawn(WHITE, Point.valueOf(6, 3));
        Empty empty = new Empty(NOTHING, Point.valueOf(7, 3));

        assertThatThrownBy(
            () -> whitePawn.direction(empty)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("목적지에 기물이 있는 경우")
    @Test
    void checkTargetExist() {
        Pawn whitePawn = new Pawn(WHITE, Point.valueOf(6, 3));
        Pawn blackPawn = new Pawn(BLACK, Point.valueOf(5, 3));

        assertThatThrownBy(
            () -> whitePawn.direction(blackPawn)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("대각선 이동 시 기물이 없는 경우")
    @Test
    void checkRightDiagonalMove() {
        Pawn whitePawn = new Pawn(WHITE, Point.valueOf(6, 3));
        Empty empty = new Empty(NOTHING, Point.valueOf(5, 4));
        Empty empty2 = new Empty(NOTHING, Point.valueOf(5, 2));
        Pawn blackPawn = new Pawn(BLACK, Point.valueOf(5, 4));

        assertEquals(Optional.of(Direction.NORTH_EAST), whitePawn.direction(blackPawn));

        assertThatThrownBy(
            () -> whitePawn.direction(empty)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> whitePawn.direction(empty2)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 첫 이동 아니면 두 칸 이동 불가 확인")
    @Test
    void checkInitialPawnImpossibleMove() {
        Pawn whitePawn = new Pawn(WHITE, Point.valueOf(5, 3));
        Empty empty = new Empty(NOTHING, Point.valueOf(3, 3));

        assertThatThrownBy(
            () -> whitePawn.direction(empty)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 첫 이동 시 두 칸 이동 가능 확인")
    @Test
    void checkInitialPawnPossibleMove() {
        Pawn whitePawn = new Pawn(WHITE, Point.valueOf(6, 3));
        Empty empty = new Empty(NOTHING, Point.valueOf(4, 3));

        assertEquals(Optional.of(Direction.NORTH), whitePawn.direction(empty));
    }
}
