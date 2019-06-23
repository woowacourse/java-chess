package chess.domain.piece;

import chess.domain.Range;
import chess.domain.board.Tile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @Test
    void isSameColorWith_test1() {
        Piece pawn = new Pawn(PieceColor.WHITE);
        Piece knight = new Knight(PieceColor.WHITE);

        assertThat(pawn.isSameColorWith(knight)).isTrue();
    }

    @Test
    void isSameColorWith_test2() {
        Piece pawn = new King(PieceColor.WHITE);
        Piece knight = new Queen(PieceColor.BLACK);

        assertThat(pawn.isSameColorWith(knight)).isFalse();
    }

    @Test
    void getColor() {
        Piece knight = new Queen(PieceColor.BLACK);

        assertThat(knight.getColor()).isEqualTo(PieceColor.BLACK);
    }

    @Test
    void getRange() {
        Piece pawn = new Pawn(PieceColor.BLACK);

        assertThat(pawn.getRange(true, Tile.of("b7"))).isEqualTo(Range.BLACK_PAWN_ATTACK_RANGE);
    }
}