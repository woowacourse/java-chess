package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DifferenceTest {

    @Test
    @DisplayName("동치성 테스트(성공)")
    void should_equals_when_same() {
        Difference difference1 = new FileDifference(1);
        Difference difference2 = new FileDifference(1);

        assertThat(difference1).isEqualTo(difference2);
    }

    @Test
    @DisplayName("동치성 테스트(실패)")
    void should_equals_when_not_same() {
        Difference difference1 = new FileDifference(1);
        Difference difference2 = new FileDifference(2);

        assertThat(difference1).isNotEqualTo(difference2);
    }

    @Test
    @DisplayName("거리를 알 수 있어야 한다")
    void should_determine_distance() {
        Difference difference = new RankDifference(3);

        assertThat(difference.hasDistance(3)).isTrue();
        assertThat(difference.hasDistance(4)).isFalse();
    }

    @Test
    @DisplayName("음수 거리를 비교할 수 없다")
    void should_not_determine_when_minus_distance() {
        Difference difference = new RankDifference(3);

        assertThatThrownBy(() -> difference.hasDistance(-3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수 거리를 비교할 수 없습니다");
    }
}
