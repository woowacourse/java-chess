package chess.domain.piece;

import chess.domain.MovingRange;
import chess.domain.board.Tile;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PieceType.*;
import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @Test
    void isSameColorWith_test1() {
        Piece pawn = PAWN.generate(PieceColor.WHITE);
        Piece knight = KNIGHT.generate(PieceColor.WHITE);

        assertThat(pawn.isSameColorWith(knight)).isTrue();
    }

    @Test
    void isSameColorWith_test2() {
        Piece pawn = KING.generate(PieceColor.WHITE);
        Piece knight = QUEEN.generate(PieceColor.BLACK);

        assertThat(pawn.isSameColorWith(knight)).isFalse();
    }

    @Test
    void getColor() {
        Piece knight = QUEEN.generate(PieceColor.BLACK);

        assertThat(knight.getColor()).isEqualTo(PieceColor.BLACK);
    }

    @Test
    void getRange() {
        Piece pawn = PAWN.generate(PieceColor.BLACK);

        assertThat(pawn.getRange(true, Tile.of("b7"))).isEqualTo(MovingRange.BLACK_PAWN_ATTACK_RANGE);
    }
}