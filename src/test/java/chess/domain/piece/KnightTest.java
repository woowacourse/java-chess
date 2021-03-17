package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    @DisplayName("나이트 생성 테스트")
    void createKnightTest() {
        assertThat(new Knight(Team.BLACK)).isInstanceOf(Knight.class);
        assertThat(new Knight(Team.WHITE)).isInstanceOf(Knight.class);
    }
}