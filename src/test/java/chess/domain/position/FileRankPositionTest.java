package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.fixture.PositionFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileRankPositionTest {
    @DisplayName("x축 대칭인 포지션을 반환할 수 있다")
    @Test
    void should_ReturnVerticalReversePosition() {
        assertThat(PositionFixtures.A1.calculateVerticalReversedPosition()).isEqualTo(PositionFixtures.A8);
    }
}
