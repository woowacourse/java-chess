package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Rank 클래스")
class RankTest {

    @Nested
    @DisplayName("ranksBetween 메서드는")
    class ranksBetween {
        @Nested
        @DisplayName("다른 Rank 객체가 주어지면")
        class with_another_ {
            final Rank from = Rank.FOUR;
            final Rank other = Rank.EIGHT;

            @Test
            @DisplayName("두 파일 사이에 있는 파일들을 반환한다")
            void it_returns_ranks() {
                List<Rank> rankPath = Rank.ranksBetween(from, other);
                assertThat(rankPath).containsOnly(Rank.FIVE, Rank.SIX, Rank.SEVEN);
            }
        }
    }

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

    @Nested
    @DisplayName("isBiggerThan 메서드는")
    class isBiggerThan {
        final Rank from = Rank.FOUR;

        @Nested
        @DisplayName("더 작 Rank 객체가 주어지면")
        class with_smaller_rank {
            final Rank other = Rank.ONE;

            @Test
            @DisplayName("false를 반환한다.")
            void it_returns_false() {
                assertThat(from.isBiggerThan(other)).isTrue();
            }
        }

        @Nested
        @DisplayName("더 큰 Rank 객체가 주어지면")
        class with_bigger_rank {
            final Rank other = Rank.EIGHT;

            @Test
            @DisplayName("false를 반환한다.")
            void it_returns_false() {
                assertThat(from.isBiggerThan(other)).isFalse();
            }
        }
    }
}
