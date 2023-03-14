package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("말은 종류를 입력받아 생성된다.")
    void create() {
        // given
        Role role = Role.PAWN;

        // when
        Piece piece = new Piece(role);

        // expected
        assertThat(piece).isNotNull();
    }
}
