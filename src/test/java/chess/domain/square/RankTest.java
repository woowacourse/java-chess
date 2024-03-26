package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("랭크")
class RankTest {

    @Test
    @DisplayName("숫자 문자를 정상적으로 변환한다.")
    void fromTest() {
        // given
        Rank rank = Rank.from('5');

        // when & then
        assertThat(rank).isEqualTo(Rank.FIVE);
    }

    @ParameterizedTest
    @ValueSource(chars = {'0', '9'})
    @DisplayName("범위 밖의 값일 경우 예외가 발생한다.")
    void validateRangeTest(char input) {
        assertThatCode(() -> Rank.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위 밖의 랭크 입니다.");
    }

    @Test
    @DisplayName("정수를 더할 수 있다.")
    void addTest() {
        // given
        Rank rank = Rank.from('5');

        // when
        Rank added = rank.add(2);

        // then
        assertThat(added).isEqualTo(Rank.THREE);
    }

    @Test
    @DisplayName("다른 랭크까지의 벡터 값을 계산한다.")
    void getVectorToTest() {
        // given
        Rank sourceRank = Rank.from('5');
        Rank targetRank = Rank.from('7');

        // when
        int vectorTo = sourceRank.getVectorTo(targetRank);

        // when & then
        assertThat(vectorTo).isEqualTo(-1);
    }

    @Test
    @DisplayName("목적지 파일부터의 거리를 계산한다.")
    void distanceFromTest() {
        // given
        Rank sourceRank = Rank.from('5');
        Rank targetRank = Rank.from('3');

        // when
        int vectorTo = sourceRank.distanceFrom(targetRank);

        // when & then
        assertThat(vectorTo).isEqualTo(2);
    }
}
