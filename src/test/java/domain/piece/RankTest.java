package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @DisplayName("Source rank와 Target rank의 거리 차이를 반환한다.")
    @Test
    void shouldReturnDifferenceBetweenSourceAndTargetWhenInputTargetToSource() {
        Rank sourceRank = Rank.FOUR;
        Rank targetRank = Rank.SEVEN;
        assertThat(sourceRank.calculateIncrement(targetRank)).isEqualTo(3);
    }

    @DisplayName("Source rank의 위쪽 값을 반환한다.")
    @Test
    void shouldReturnNextRankWhenRequest() {
        assertThat(Rank.ONE.getNext()).isEqualTo(Rank.TWO);
    }

    @DisplayName("Rank가 8에서 다음 위치를 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenLastRankRequestGetNext() {
        assertThatThrownBy(() -> Rank.EIGHT.getNext())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("서버 내부 에러 - 다음 Rank는 존재하지 않습니다");
    }

    @DisplayName("Source rank의 아래 값을 반환한다.")
    @Test
    void shouldReturnPreviousRankWhenRequest() {
        assertThat(Rank.SIX.getPrevious()).isEqualTo(Rank.FIVE);
    }

    @DisplayName("Rank가 1에서 이전 위치를 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenFirstRankRequestGetPrevious() {
        assertThatThrownBy(() -> Rank.ONE.getPrevious())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("서버 내부 에러 - 이전 Rank는 존재하지 않습니다");
    }
}
