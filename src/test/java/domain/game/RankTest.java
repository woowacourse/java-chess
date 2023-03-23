package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Rank는")
public class RankTest {

    @DisplayName("FOUR와 SEVEN 거리 차이로 3을 반환한다.")
    @Test
    void shouldReturnDifferenceBetweenSourceAndTargetWhenInputTargetToSource() {
        Rank sourceRank = Rank.FOUR;
        Rank targetRank = Rank.SEVEN;
        assertThat(sourceRank.calculateIncrement(targetRank)).isEqualTo(3);
    }

    @DisplayName("ONE의 다음 값으로 TWO를 반환한다.")
    @Test
    void shouldReturnNextRankWhenRequest() {
        assertThat(Rank.ONE.getNext()).isEqualTo(Rank.TWO);
    }

    @DisplayName("EIGHT에서 다음 위치를 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenLastRankRequestGetNext() {
        assertThatThrownBy(() -> Rank.EIGHT.getNext())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("서버 내부 에러 - 다음 Rank는 존재하지 않습니다");
    }

    @DisplayName("SIX의 이전 값으로 FIVE를 반환한다.")
    @Test
    void shouldReturnPreviousRankWhenRequest() {
        assertThat(Rank.SIX.getPrevious()).isEqualTo(Rank.FIVE);
    }

    @DisplayName("ONE에서 이전 값을 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenFirstRankRequestGetPrevious() {
        assertThatThrownBy(() -> Rank.ONE.getPrevious())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("서버 내부 에러 - 이전 Rank는 존재하지 않습니다");
    }
}
