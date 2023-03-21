package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyPieceTest {

    @Test
    @DisplayName("EmptyPiece는 Neutrality 팀만을 가진다.")
    void validateTeamTest_success() {
        assertThatNoException().isThrownBy(EmptyPiece::new);
    }
}
