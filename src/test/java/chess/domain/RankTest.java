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
            final Rank rank1 = Rank.FOUR;
            final Rank rank2 = Rank.EIGHT;

            @Test
            @DisplayName("두 랭크간의 거리를 계산한다")
            void it_returns_distance() {
                assertThat(rank1.distanceTo(rank2)).isEqualTo(4);
                assertThat(rank2.distanceTo(rank1)).isEqualTo(4);
            }
        }
    }
}
