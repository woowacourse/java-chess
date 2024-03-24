package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @DisplayName("int입력받아 Rank을 생성한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, ONE", "2, TWO", "3, THREE", "4, FOUR", "5, FIVE", "6, SIX", "7, SEVEN", "8, EIGHT"})
    void from(final int input, final Rank expected) {
        // given && when
        final Rank rank = Rank.fromNumber(input);

        // then
        Assertions.assertThat(rank).isEqualTo(expected);
    }

    @DisplayName("유효하지 않는 int을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 9, 10, -1})
    void invalidRank(final int input) {
        Assertions.assertThatThrownBy(() -> Rank.fromNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효한 랭크 입력이 아닙니다.");
    }


    @DisplayName("현재보다 1칸 위쪽인 경우의 랭크를 반환한다..")
    @Test
    void up() {
        // given && when
        Rank rank = Rank.THREE.up();

        // then
        assertThat(rank).isEqualTo(Rank.FOUR);
    }

    @DisplayName("다른 파일과의 절대값 거리 차이를 반환한다.")
    @Test
    void getDistance() {
        // given
        Rank rank = Rank.THREE;

        // when
        int distance = rank.getDistance(Rank.FIVE);

        // then
        assertThat(distance).isEqualTo(2);
    }

    @DisplayName("더 큰 랭크인지 확인한다.")
    @Test
    void isBigger() {
        // given
        Rank rank = Rank.THREE;

        // when
        boolean isBigger = rank.isBigger(Rank.ONE);

        // then
        assertThat(isBigger).isTrue();
    }

    @DisplayName("더 큰 랭크가 아닌지 확인한다.")
    @Test
    void isNotBigger() {
        // given
        Rank rank = Rank.THREE;

        // when
        boolean isBigger = rank.isBigger(Rank.SIX);

        // then
        assertThat(isBigger).isFalse();
    }
}
