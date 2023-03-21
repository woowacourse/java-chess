package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Rank 클래스")
class RankTest {
    @Nested
    @DisplayName("distanceTo 메서드는")
    class distanceTo {
        @Nested
        @DisplayName("다른 Rank 객체가 주어지면")
        class with_another_rank {
            final Rank from = Rank.FOUR;
            final Rank other = Rank.EIGHT;

            @Test
            @DisplayName("두 랭크간의 거리를 계산한다")
            void it_returns_distance() {
                assertThat(from.distanceTo(other)).isEqualTo(4);
            }
        }
    }
}
