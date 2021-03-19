package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @Test
    @DisplayName("진영에 따라 이름 생성 기능")
    void checkName() {
        assertThat(new Bishop(Team.BLACK).getName()).isEqualTo("B");
        assertThat(new King(Team.BLACK).getName()).isEqualTo("K");
        assertThat(new Knight(Team.BLACK).getName()).isEqualTo("N");
        assertThat(new Pawn(Team.BLACK).getName()).isEqualTo("P");
        assertThat(new Queen(Team.BLACK).getName()).isEqualTo("Q");
        assertThat(new Rook(Team.BLACK).getName()).isEqualTo("R");
        assertThat(new Bishop(Team.WHITE).getName()).isEqualTo("b");
        assertThat(new King(Team.WHITE).getName()).isEqualTo("k");
    }
}