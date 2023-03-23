package domain.piece;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @DisplayName("기물들은 이름을 가진다")
    @Test
    void pieceHasName() {
        //given
        final Piece king = new King(Team.BLACK);
        final Piece queen = new Queen(Team.BLACK);
        final Piece knight = new Knight(Team.BLACK);
        final Piece bishop = new Bishop(Team.BLACK);
        final Piece rook = new Rook(Team.BLACK);
        final Piece pawn = new Pawn(Team.BLACK);

        //when

        //then
        Assertions.assertAll(
                () -> assertThat(king.getName()).isEqualTo("K"),
                () -> assertThat(queen.getName()).isEqualTo("Q"),
                () -> assertThat(knight.getName()).isEqualTo("N"),
                () -> assertThat(bishop.getName()).isEqualTo("B"),
                () -> assertThat(rook.getName()).isEqualTo("R"),
                () -> assertThat(pawn.getName()).isEqualTo("P"));
    }

    @DisplayName("기물들은 팀을 가진다")
    @Test
    void pieceHasTeam() {
        //given
        final Piece black = new King(Team.BLACK);
        final Piece white = new King(Team.WHITE);

        //when

        //then
        Assertions.assertAll(
                () -> assertThat(black.isBlack()).isTrue(),
                () -> assertThat(white.isBlack()).isFalse());
    }
}
