package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("기물들은 이름을 가진다")
    @Test
    void kingName() {
        //given
        final Piece king = new King();
        final Piece queen = new Queen();
        final Piece knight = new Knight();
        final Piece bishop = new Bishop();
        final Piece rook = new Rook();
        final Piece pawn = new Pawn();

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
