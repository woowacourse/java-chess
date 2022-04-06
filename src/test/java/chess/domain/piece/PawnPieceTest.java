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
    @CsvSource({"22, 23", "82, 83"})
    @DisplayName("target에 기물이 없는 경우 앞으로 move 할 수 있는지 확인한다.")
    void canMoveForwardWhite(String source, String target) {
        Piece pawn = new PawnPiece(Color.WHITE);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(pawn.isMovable(from, to, true));
    }

    @ParameterizedTest
    @CsvSource({"42, 53", "42, 33"})
    @DisplayName("target에 기물이 있는 경우 대각선으로 move 할 수 있는지 확인한다.")
    void canMoveDiagonalWhite(String source, String target) {
        Piece pawn = new PawnPiece(Color.WHITE);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(pawn.isMovable(from, to, false));
    }

    @ParameterizedTest
    @CsvSource({"47, 46", "87, 86"})
    @DisplayName("target에 기물이 없는 경우 앞으로 move 할 수 있는지 확인한다.")
    void canMoveForwardBlack(String source, String target) {
        Piece pawn = new PawnPiece(Color.BLACK);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(pawn.isMovable(from, to, true));
    }

    @ParameterizedTest
    @CsvSource({"47, 56", "47, 36"})
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
        Position from = Position.create("12");
        Position to = Position.create("14");

        assertTrue(pawn.isMovable(from, to, true));
    }

    @Test
    @DisplayName("초기 위치에서 앞으로 두칸 move할 수 있는지 확인한다.")
    void canMoveTwiceBlack() {
        Piece pawn = new PawnPiece(Color.BLACK);
        Position from = Position.create("17");
        Position to = Position.create("15");

        assertTrue(pawn.isMovable(from, to, true));
    }

    @ParameterizedTest
    @CsvSource({"42, 45", "42, 41", "42, 54", "42, 31"})
    @DisplayName("move 할 수 없는지 확인한다.")
    void cantMoveWhite(String source, String target) {
        Piece pawn = new PawnPiece(Color.WHITE);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertFalse(pawn.isMovable(from, to, false));
    }

    @ParameterizedTest
    @CsvSource({"47, 44", "47, 48", "47, 55", "47, 38"})
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
        Position from = Position.create("27");
        Position to = Position.create("36");

        assertFalse(pawn.isMovable(from, to, true));
    }

    @Test
    @DisplayName("폰은 target 위치에 기물이 있을 때 앞으로 이동할 수 없다.")
    void cantMoveForwardWhenTarget() {
        Piece pawn = new PawnPiece(Color.BLACK);
        Position from = Position.create("27");
        Position to = Position.create("26");

        assertFalse(pawn.isMovable(from, to, false));
    }
}
