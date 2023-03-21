package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @DisplayName("text에 맞는 domain.piece.Rank 인스턴스를 반환한다.")
    @Test
    void shouldReturnAppropriateFileWhenIncomeText() {
        Rank rank = Rank.from("1");
        assertThat(rank).isEqualTo(Rank.ONE);
    }

    @DisplayName("유효하지 않은 domain.piece.Rank 텍스트 입력 시 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenInvalidRankText() {
        assertThatThrownBy(() -> Rank.from("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 rank입니다.");
    }

    @DisplayName("source rank와 target rank의 거리 차이를 반환한다.")
    @Test
    void shouldReturnDifferenceBetweenSourceAndTargetWhenInputTargetToSource() {
        Rank sourceRank = Rank.from("4");
        Rank targetRank = Rank.from("7");
        assertThat(sourceRank.calculateIncrement(targetRank)).isEqualTo(3);
    }

    @DisplayName("source rank의 위쪽 값을 반환한다.")
    @Test
    void shouldReturnNextRankWhenRequest() {
        assertThat(Rank.ONE.getNext()).isEqualTo(Rank.TWO);
    }

    @DisplayName("rank가 8에서 다음 위치를 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenLastRankRequestGetNext() {
        assertThatThrownBy(() -> Rank.EIGHT.getNext())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("서버 내부 에러 - 다음 Rank는 존재하지 않습니다");
    }

    @DisplayName("source rank의 아래 값을 반환한다.")
    @Test
    void shouldReturnPreviousRankWhenRequest() {
        assertThat(Rank.SIX.getPrevious()).isEqualTo(Rank.FIVE);
    }

    @DisplayName("rank가 1에서 이전 위치를 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenFirstRankRequestGetPrevious() {
        assertThatThrownBy(() -> Rank.ONE.getPrevious())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("서버 내부 에러 - 이전 Rank는 존재하지 않습니다");
    }
}
