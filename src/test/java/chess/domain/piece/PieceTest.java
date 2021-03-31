package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @Test
    @DisplayName("진영에 따라 이름 생성 기능")
    void checkName() {
        assertThat(new Bishop(Team.BLACK).name()).isEqualTo("black_b");
        assertThat(new King(Team.BLACK).name()).isEqualTo("black_k");
        assertThat(new Knight(Team.BLACK).name()).isEqualTo("black_n");
        assertThat(new Pawn(Team.BLACK).name()).isEqualTo("black_p");
        assertThat(new Queen(Team.BLACK).name()).isEqualTo("black_q");
        assertThat(new Rook(Team.BLACK).name()).isEqualTo("black_r");
        assertThat(new Bishop(Team.WHITE).name()).isEqualTo("white_b");
        assertThat(new King(Team.WHITE).name()).isEqualTo("white_k");
    }
}