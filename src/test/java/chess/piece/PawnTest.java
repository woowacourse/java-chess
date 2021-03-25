package chess.piece;

import static chess.domain.board.Board.*;
import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.board.Point;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Pawn;
import chess.domain.piece.kind.Piece;

public class PawnTest {
    private Map<Point, Piece> board;

    @BeforeEach
    void setUp() {
        board = new HashMap<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board.put(Point.valueOf(i, j), PieceType.findPiece(i, j));
            }
        }
    }

    @DisplayName("Pawn 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    void create(int column) {
        Pawn blackPawn = new Pawn(BLACK);
        assertThat(board.get(Point.valueOf(1, column))).isEqualTo(blackPawn);
        Pawn whitePawn = new Pawn(WHITE);
        assertThat(board.get(Point.valueOf(6, column))).isEqualTo(whitePawn);
    }

    @DisplayName("검은 Pawn의 불가능한 방향 확인")
    @Test
    void checkBlackPawnImpossibleMove() {
        Pawn blackPawn = new Pawn(BLACK);
        assertThatThrownBy(
            () -> blackPawn.checkCorrectDirection(Point.valueOf(1, 3).makeDirection(Point.valueOf(0, 3))))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("하얀 Pawn의 불가능한 방향 확인")
    @Test
    void checkWhitePawnImpossibleMove() {
        Pawn whitePawn = new Pawn(WHITE);
        assertThatThrownBy(
            () -> whitePawn.checkCorrectDirection(Point.valueOf(6, 3).makeDirection(Point.valueOf(7, 3))))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("목적지에 기물이 있는 경우")
    @Test
    void checkTargetExist() {
        Pawn whitePawn = new Pawn(WHITE);

        assertThatThrownBy(
            () -> whitePawn.checkCorrectDistance(Point.valueOf(6, 3), Point.valueOf(5, 3), new Pawn(BLACK)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("대각선 이동 시 기물이 없는 경우")
    @Test
    void checkRightDiagonalMove() {
        Pawn whitePawn = new Pawn(WHITE);

        assertThatThrownBy(
            () -> whitePawn.checkCorrectDistance(Point.valueOf(6, 3), Point.valueOf(5, 4), new Empty(NOTHING)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(
            () -> whitePawn.checkCorrectDistance(Point.valueOf(5, 2), Point.valueOf(5, 4), new Empty(NOTHING)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 첫 이동 아니면 두 칸 이동 불가 확인")
    @Test
    void checkInitialPawnImpossibleMove() {
        Pawn whitePawn = new Pawn(WHITE);

        assertThatThrownBy(
            () -> whitePawn.checkCorrectDistance(Point.valueOf(5, 3), Point.valueOf(3, 4), new Empty(NOTHING)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 첫 이동 시 두 칸 이동 가능 확인")
    @Test
    void checkInitialPawnPossibleMove() {
        Pawn whitePawn = new Pawn(WHITE);

        assertDoesNotThrow(
            () -> whitePawn.checkCorrectDistance(Point.valueOf(6, 3), Point.valueOf(4, 3), new Empty(NOTHING)));
    }
}
