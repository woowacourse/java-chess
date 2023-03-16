package chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("입력받은 값이 더해진 Rank를 반환한다.")
    void getAddedRank_success() {
        // given
        final Rank rank = Rank.FOUR;

        // when
        final Rank addedRank = rank.getAddedRank(2);

        // then
        assertThat(addedRank).isSameAs(Rank.SIX);
    }

    @Test
    @DisplayName("입력받은 값이 더해진 Rank가 존재하지 않으면 예외를 던진다.")
    void getAddedRank_fail() {
        // given
        final Rank rank = Rank.FOUR;

        // when, then
        assertThatThrownBy(() -> rank.getAddedRank(5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 더한 랭크 값이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("대상 Rank과의 값 차이를 반환한다.")
        // TODO: 2023/03/15 테스트 케이스 추가(Parameterized Test)
    void getValueDiff() {
        // given
        Rank rank = Rank.ONE;
        Rank targetRank = Rank.FIVE;

        // when
        final int valueDiff = rank.getValueDiff(targetRank);

        // then
        assertThat(valueDiff).isEqualTo(4);
    }

    @Test
    @DisplayName("대상 Rank과의 값 차이에 따른 수직 좌표 값을 반환한다.")
    void getValuePoint() {
        // given
        Rank rank = Rank.ONE;
        Rank targetRank = Rank.FIVE;

        // when
        final int valuePoint = rank.getValuePoint(targetRank);

        // then
        assertThat(valuePoint).isEqualTo(1);
    }
}
