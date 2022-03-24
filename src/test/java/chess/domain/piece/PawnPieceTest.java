package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnPieceTest {

    // 전진 ->  블랙: south, 화이트: north
    // 대각선 -> 블랙: south east, west
    //
    // rank 2에서 출발할때는 1칸 또는 2칸 가능

    @ParameterizedTest
    @CsvSource({"d2, d3", "d2, e3", "d2, c3"})
    @DisplayName("move 할 수 있는지 확인한다.")
    void canMoveWhite(String source, String target) {
        Piece pawn = new PawnPiece(Color.WHITE);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(pawn.isMovable(from, to, false));
    }

    @ParameterizedTest
    @CsvSource({"d7, d6", "d7, e6", "d7, c6"})
    @DisplayName("move 할 수 있는지 확인한다.")
    void canMoveBlack(String source, String target) {
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

        assertTrue(pawn.isMovable(from, to, false));
    }

    @Test
    @DisplayName("초기 위치에서 앞으로 두칸 move할 수 있는지 확인한다.")
    void canMoveTwiceBlack() {
        Piece pawn = new PawnPiece(Color.BLACK);
        Position from = Position.create("a7");
        Position to = Position.create("a5");

        assertTrue(pawn.isMovable(from, to, false));
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
}