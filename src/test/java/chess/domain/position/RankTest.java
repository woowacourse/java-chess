package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @ParameterizedTest
    @CsvSource({"SEVEN, EIGHT", "ONE, TWO"})
    @DisplayName("다음 북쪽 위치를 알 수 있다.")
    void toNorthTest(Rank before, Rank after) {
        assertThat(before.toNorth()).isEqualTo(after);
    }

    @Test
    @DisplayName("북쪽으로 갈 수 없으면, 예외가 발생한다.")
    void toNorthTest_whenCant() {
        assertThatThrownBy(Rank.EIGHT::toNorth)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("북쪽으로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"EIGHT, SEVEN", "TWO, ONE"})
    @DisplayName("다음 남쪽 위치를 알 수 있다.")
    void toSouthTest(Rank before, Rank after) {
        assertThat(before.toSouth()).isEqualTo(after);
    }

    @Test
    @DisplayName("남쪽으로 갈 수 없으면, 예외가 발생한다.")
    void toSouthTest_whenCant() {
        assertThatThrownBy(Rank.ONE::toSouth)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("남쪽으로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"ONE, EIGHT, 7", "EIGHT, ONE, -7", "THREE, FOUR, 1", "FOUR, THREE, -1"})
    @DisplayName("두 랭크의 차이를 알 수 있다.")
    void calculateDifferenceTest(Rank before, Rank after, int expected) {
        assertThat(before.calculateDifference(after)).isEqualTo(expected);
    }
}
