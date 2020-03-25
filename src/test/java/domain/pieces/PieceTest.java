package domain.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import domain.team.Team;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    void getInitial_White() {
        Piece chesspiece = new Pawn(Team.WHITE);
        assertThat(chesspiece.getInitial()).isEqualTo("p");
    }

    @Test
    void getInitial_Black() {
        Piece chesspiece = new Pawn(Team.BLACK);
        assertThat(chesspiece.getInitial()).isEqualTo("P");
    }
}