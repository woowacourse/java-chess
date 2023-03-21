package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.fixture.TestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("기물들은 이름을 가진다")
    @Test
    void pieceHasName() {
        //given
        final Piece king = TestFixture.BLACK_KING;
        final Piece queen = TestFixture.BLACK_QUEEN;
        final Piece knight = TestFixture.BLACK_KNIGHT;
        final Piece bishop = TestFixture.BLACK_BISHOP;
        final Piece rook = TestFixture.BLACK_ROOK;
        final Piece pawn = TestFixture.BLACK_PAWN;

        //when

        //then
        assertThat(king.getName()).isEqualTo("K");
        assertThat(queen.getName()).isEqualTo("Q");
        assertThat(knight.getName()).isEqualTo("N");
        assertThat(bishop.getName()).isEqualTo("B");
        assertThat(rook.getName()).isEqualTo("R");
        assertThat(pawn.getName()).isEqualTo("P");
    }

    @DisplayName("기물들은 팀을 가진다")
    @Test
    void pieceHasTeam() {
        //given
        final Piece black = TestFixture.BLACK_KING;
        final Piece white = TestFixture.WHITE_KING;

        //when

        //then
        assertThat(black.isBlack()).isTrue();
        assertThat(white.isBlack()).isFalse();
    }
}
