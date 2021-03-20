package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Test
    @DisplayName("비숍 생성 테스트")
    void createTest() {
        assertThat(Bishop.createBlack()).isInstanceOf(Bishop.class);
        assertThat(Bishop.createWhite()).isInstanceOf(Bishop.class);
    }
}