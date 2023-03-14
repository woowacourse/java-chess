package domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("기물들은 이름을 가진다")
    @Test
    void kingName() {
        //given
        final Piece king = King.create();
        final Piece queen = Queen.create();
        final Piece knight = Knight.create();
        final Piece bishop = Bishop.create();
        final Piece rook = Rook.create();
        final Piece pawn = Pawn.create();

        //when

        //then
        assertThat(king.getName()).isEqualTo("K");
        assertThat(queen.getName()).isEqualTo("Q");
        assertThat(knight.getName()).isEqualTo("N");
        assertThat(bishop.getName()).isEqualTo("B");
        assertThat(rook.getName()).isEqualTo("R");
        assertThat(pawn.getName()).isEqualTo("P");
    }
}
