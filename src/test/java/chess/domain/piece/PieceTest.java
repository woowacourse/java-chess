package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @Test
    @DisplayName("진영에 따라 이름 생성 기능")
    void checkName() {
        assertThat(new Bishop(true).getName()).isEqualTo("B");
        assertThat(new King(true).getName()).isEqualTo("K");
        assertThat(new Knight(true).getName()).isEqualTo("N");
        assertThat(new Pawn(true).getName()).isEqualTo("P");
        assertThat(new Queen(true).getName()).isEqualTo("Q");
        assertThat(new Rook(true).getName()).isEqualTo("R");
        assertThat(new Bishop(false).getName()).isEqualTo("b");
        assertThat(new King(false).getName()).isEqualTo("k");
    }
}