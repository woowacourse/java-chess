package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    @DisplayName("나이트 생성 테스트")
    void createKnightTest() {
        assertThat(Knight.createBlack()).isInstanceOf(Knight.class);
        assertThat(Knight.createWhite()).isInstanceOf(Knight.class);
    }
}