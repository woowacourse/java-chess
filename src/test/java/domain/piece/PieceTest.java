package domain.piece;


import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.info.Color;
import domain.piece.info.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("피스의 색이 흰색인지 확인한다")
    void color() {
        final Piece rook = new Rook(Color.WHITE, Type.ROOK);

        assertThat(rook.isWhite()).isTrue();
    }
}
