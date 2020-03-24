package domain.chesspiece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.team.Team;
import org.junit.jupiter.api.Test;

public class ChesspieceTest {

    @Test
    void getInitial_White() {
        Chesspiece chesspiece = new Pawn(Team.WHITE);
        assertThat(chesspiece.getInitial()).isEqualTo("p");
    }

    @Test
    void getInitial_Black() {
        Chesspiece chesspiece = new Pawn(Team.BLACK);
        assertThat(chesspiece.getInitial()).isEqualTo("P");
    }
}