package chess.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesTest {
    @Test
    @DisplayName("32개의 piece 위치를 초기화 한다.")
    void initPieces() {
        assertThat(Pieces.init()).hasSize(32);
    }
}
