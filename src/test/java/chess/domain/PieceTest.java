package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("색과 종류를 가진 말을 생성")
    void piece_createWith_colorAndType() {
        final var piece = new Piece(Color.BLACK, new Pawn());
        assertThat(piece).isNotNull();
    }

}
