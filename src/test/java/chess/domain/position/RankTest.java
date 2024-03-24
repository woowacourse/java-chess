package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("올바르지 않은 행을 변환하는 경우 예외를 발생한다.")
    void invalidRankNumberTest(int rankNumber) {
        Assertions.assertThatThrownBy(() -> Rank.from(rankNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 행 번호입니다.");
    }

    @Test
    @DisplayName("랭크의 차이를 올바르게 계산한다")
    void subtractTest() {
        // given
        Rank back = Rank.from(1);
        Rank source = Rank.from(3);
        Rank front = Rank.from(5);
        // when
        int subtractBack = source.subtract(back);
        int subtractFront = source.subtract(front);
        // then
        assertAll(
                () -> assertThat(subtractBack).isEqualTo(2),
                () -> assertThat(subtractFront).isEqualTo(-2)
        );
    }

    @Test
    @DisplayName("랭크의 차이를 받아 해당 랭크을 반환한다")
    void createRankByDifferenceOfTest() {
        // given
        Rank source = Rank.from(3);
        // when
        Rank back = source.createRankByDifferenceOf(2);
        Rank front = source.createRankByDifferenceOf(-2);
        // then
        assertAll(
                () -> assertThat(back).isEqualTo(Rank.from(5)),
                () -> assertThat(front).isEqualTo(Rank.from(1))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 9, 1000})
    @DisplayName("랭크의 차이를 받아 반환한 랭크이 범위에 벗어난다면 예외를 발생한다.")
    void createRankByDifferenceOfExceptionTest(int difference) {
        // given
        Rank source = Rank.from(1);
        // when, then
        assertThatThrownBy(() -> source.createRankByDifferenceOf(difference))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 행 번호입니다.");
    }
}
