package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @Test
    @DisplayName("진영에 따라 이름 생성 기능")
    void checkName() {
       final Piece pawn = new Pawn(false);
       assertThat(pawn.getName()).isEqualTo("p");
    }
}