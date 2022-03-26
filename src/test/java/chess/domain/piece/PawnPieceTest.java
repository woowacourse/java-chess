package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnPieceTest {

    @ParameterizedTest
    @CsvSource({"d2, d3", "h2, h3"})
    @DisplayName("target에 기물이 없는 경우 앞으로 move 할 수 있는지 확인한다.")
    void canMoveForwardWhite(String source, String target) {
        Piece pawn = new PawnPiece(Color.WHITE);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(pawn.isMovable(from, to, true));
    }

    @ParameterizedTest
    @CsvSource({"d2, e3", "d2, c3"})
    @DisplayName("target에 기물이 있는 경우 대각선으로 move 할 수 있는지 확인한다.")
    void canMoveDiagonalWhite(String source, String target) {
        Piece pawn = new PawnPiece(Color.WHITE);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(pawn.isMovable(from, to, false));
    }

    @ParameterizedTest
    @CsvSource({"d7, d6", "h7, h6"})
    @DisplayName("target에 기물이 없는 경우 앞으로 move 할 수 있는지 확인한다.")
    void canMoveForwardBlack(String source, String target) {
        Piece pawn = new PawnPiece(Color.BLACK);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(pawn.isMovable(from, to, true));
    }

    @ParameterizedTest
    @CsvSource({"d7, e6", "d7, c6"})
    @DisplayName("target에 기물이 있는 경우 대각선으로 move 할 수 있는지 확인한다.")
    void canMoveDiagonalBlack(String source, String target) {
        Piece pawn = new PawnPiece(Color.BLACK);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(pawn.isMovable(from, to, false));
    }

    @Test
    @DisplayName("초기 위치에서 앞으로 두칸 move할 수 있는지 확인한다.")
    void canMoveTwiceWhite() {
        Piece pawn = new PawnPiece(Color.WHITE);
        Position from = Position.create("a2");
        Position to = Position.create("a4");

        assertTrue(pawn.isMovable(from, to, true));
    }

    @Test
    @DisplayName("초기 위치에서 앞으로 두칸 move할 수 있는지 확인한다.")
    void canMoveTwiceBlack() {
        Piece pawn = new PawnPiece(Color.BLACK);
        Position from = Position.create("a7");
        Position to = Position.create("a5");

        assertTrue(pawn.isMovable(from, to, true));
    }

    @ParameterizedTest
    @CsvSource({"d2, d5", "d2, d1", "d2, f4", "d2, c1"})
    @DisplayName("move 할 수 없는지 확인한다.")
    void cantMoveWhite(String source, String target) {
        Piece pawn = new PawnPiece(Color.WHITE);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertFalse(pawn.isMovable(from, to, false));
    }

    @ParameterizedTest
    @CsvSource({"d7, d4", "d7, d8", "d7, f5", "d7, c8"})
    @DisplayName("move 할 수 없는지 확인한다.")
    void cantMoveBlack(String source, String target) {
        Piece pawn = new PawnPiece(Color.BLACK);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertFalse(pawn.isMovable(from, to, false));
    }

    @Test
    @DisplayName("폰은 target 위치에 기물이 없을 때 대각선으로 이동할 수 없다.")
    void cantMoveDiagonalWhenEmptyTarget() {
        Piece pawn = new PawnPiece(Color.BLACK);
        Position from = Position.create("b7");
        Position to = Position.create("c6");

        assertFalse(pawn.isMovable(from, to, true));
    }

    @Test
    @DisplayName("폰은 target 위치에 기물이 있을 때 앞으로 이동할 수 없다.")
    void cantMoveForwardWhenTarget() {
        Piece pawn = new PawnPiece(Color.BLACK);
        Position from = Position.create("b7");
        Position to = Position.create("b6");

        assertFalse(pawn.isMovable(from, to, false));
    }
}