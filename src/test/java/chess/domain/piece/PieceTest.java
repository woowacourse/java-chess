package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @Test
    @DisplayName("진영에 따라 이름 생성 기능")
    void checkName() {
        assertThat(new Bishop(Team.BLACK).name()).isEqualTo("B");
        assertThat(new King(Team.BLACK).name()).isEqualTo("K");
        assertThat(new Knight(Team.BLACK).name()).isEqualTo("N");
        assertThat(new Pawn(Team.BLACK).name()).isEqualTo("P");
        assertThat(new Queen(Team.BLACK).name()).isEqualTo("Q");
        assertThat(new Rook(Team.BLACK).name()).isEqualTo("R");
        assertThat(new Bishop(Team.WHITE).name()).isEqualTo("b");
        assertThat(new King(Team.WHITE).name()).isEqualTo("k");
    }
}