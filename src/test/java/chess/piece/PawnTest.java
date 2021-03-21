package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PawnTest {
    @DisplayName("Pawn 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    void create(int column) {
        Pawn blackPawn = new Pawn(BLACK, Point.of(1, column));
        assertThat(PieceType.findPiece(1, column)).isEqualTo(blackPawn);
        Pawn whitePawn = new Pawn(WHITE, Point.of(6, column));
        assertThat(PieceType.findPiece(6, column)).isEqualTo(whitePawn);
    }

    @DisplayName("검은 Pawn의 불가능한 방향 확인")
    @Test
    void checkBlackPawnImpossibleMove() {
        Point source = Point.of(1, 3);
        Pawn blackPawn = new Pawn(BLACK, source);
        Empty empty = new Empty(NOTHING, Point.of(0, 3));

        Direction direction = Direction.findDirection(source, Point.of(0, 3));

        assertThatThrownBy(
                () -> blackPawn.validateMovable(direction, empty)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("하얀 Pawn의 불가능한 방향 확인")
    @Test
    void checkWhitePawnImpossibleMove() {
        Point source = Point.of(6, 3);
        Pawn whitePawn = new Pawn(WHITE, source);
        Empty empty = new Empty(NOTHING, Point.of(7, 3));

        Direction direction = Direction.findDirection(source, Point.of(7, 3));

        assertThatThrownBy(
                () -> whitePawn.validateMovable(direction, empty)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("대각선 이동 시 기물이 없는 경우")
    @Test
    void checkRightDiagonalMove() {
        Pawn whitePawn = new Pawn(WHITE, Point.of(6, 3));
        Empty empty = new Empty(NOTHING, Point.of(5, 4));

        Pawn blackPawn = new Pawn(BLACK, Point.of(5, 4));
        Empty empty2 = new Empty(NOTHING, Point.of(4, 3));

        Direction direction1 = Direction.findDirection(Point.of(6, 3), Point.of(5, 4));
        Direction direction2 = Direction.findDirection(Point.of(5, 4), Point.of(4, 3));

        assertThatThrownBy(
                () -> whitePawn.validateMovable(direction1, empty)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> blackPawn.validateMovable(direction2, empty2)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 첫 이동 아니면 두 칸 이동 불가 확인")
    @Test
    void checkInitialPawnImpossibleMove() {
        Pawn whitePawn = new Pawn(WHITE, Point.of(2, 3));
        Empty empty = new Empty(NOTHING, Point.of(4, 3));

        Direction direction = Direction.findDirection(Point.of(2, 3), Point.of(4, 3));

        assertThatThrownBy(
                () -> whitePawn.validateMovable(direction, empty)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 첫 이동 시 두 칸 이동 가능 확인")
    @Test
    void checkInitialPawnPossibleMove() {
        Pawn whitePawn = new Pawn(WHITE, Point.of(6, 3));
        Empty empty = new Empty(NOTHING, Point.of(4, 3));

        Direction direction = Direction.findDirection(Point.of(6, 3), Point.of(4, 3));

        assertDoesNotThrow(() -> whitePawn.validateMovable(direction, empty));
    }
}
