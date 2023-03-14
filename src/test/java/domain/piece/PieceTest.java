package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("기물들은 이름을 가진다")
    @Test
    void kingName() {
        //given
        final Piece king = King.create(Position.from("a1"));
        final Piece queen = Queen.create(Position.from("a1"));
        final Piece knight = Knight.create(Position.from("a1"));
        final Piece bishop = Bishop.create(Position.from("a1"));
        final Piece rook = Rook.create(Position.from("a1"));
        final Piece pawn = Pawn.create(Position.from("a1"));

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
