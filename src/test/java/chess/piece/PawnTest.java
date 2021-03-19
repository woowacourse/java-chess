package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.ChessGame.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {
    @DisplayName("Pawn 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    void create(int column) {
        Pawn blackPawn = new Pawn("P", "BLACK", Point.valueOf(1, column));
        assertThat(Pieces.findPiece(1, column)).isEqualTo(blackPawn);
        Pawn whitePawn = new Pawn("p", "WHITE", Point.valueOf(6, column));
        assertThat(Pieces.findPiece(6, column)).isEqualTo(whitePawn);
    }

    @DisplayName("검은 Pawn의 불가능한 방향 확인")
    @Test
    void checkBlackPawnImpossibleMove() {
        Pawn blackPawn = new Pawn("P", BLACK, Point.valueOf(1, 3));
        Empty empty = new Empty(".", null, Point.valueOf(0, 3));

        assertThatThrownBy(
                () -> blackPawn.direction(empty)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("하얀 Pawn의 불가능한 방향 확인")
    @Test
    void checkWhitePawnImpossibleMove() {
        Pawn whitePawn = new Pawn("p", "WHITE", Point.valueOf(6, 3));
        Empty empty = new Empty(".", null, Point.valueOf(7, 3));

        assertThatThrownBy(
                () -> whitePawn.direction(empty)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("목적지에 기물이 있는 경우")
    @Test
    void checkTargetExist() {
        Pawn whitePawn = new Pawn("p", "WHITE", Point.valueOf(6, 3));
        Pawn blackPawn = new Pawn("P", BLACK, Point.valueOf(5, 3));

        assertThatThrownBy(
                () -> whitePawn.direction(blackPawn)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("대각선 이동 시 기물이 없는 경우")
    @Test
    void checkRightDiagonalMove() {
        Pawn whitePawn = new Pawn("p", "WHITE", Point.valueOf(6, 3));
        Empty empty = new Empty(".", null, Point.valueOf(5, 4));
        Empty empty2 = new Empty(".", null, Point.valueOf(5, 2));
        Pawn blackPawn = new Pawn("P", BLACK, Point.valueOf(5, 4));

        assertEquals(whitePawn.direction(blackPawn), Direction.NORTH_EAST);

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
        Pawn whitePawn = new Pawn("p", "WHITE", Point.valueOf(5, 3));
        Empty empty = new Empty(".", null, Point.valueOf(3, 3));

        assertThatThrownBy(
                () -> whitePawn.direction(empty)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 첫 이동 시 두 칸 이동 가능 확인")
    @Test
    void checkInitialPawnPossibleMove() {
        Pawn whitePawn = new Pawn("p", "WHITE", Point.valueOf(6, 3));
        Empty empty = new Empty(".", null, Point.valueOf(4, 3));

        assertEquals(whitePawn.direction(empty), Direction.NORTH);
    }
}
