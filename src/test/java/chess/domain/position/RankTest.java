package chess.domain.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {
    @DisplayName("잘못된 y 좌표 입력값에 대해 예외 처리 테스트")
    @Test
    void ofTest() {
        Assertions.assertThatThrownBy(() -> {
            Rank.of("9");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}