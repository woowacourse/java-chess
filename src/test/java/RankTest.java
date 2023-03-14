import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @DisplayName("text에 맞는 Rank 인스턴스를 반환한다.")
    @Test
    void shouldReturnAppropriateRankWhenIncomeText() {
        Rank rank = Rank.from("a");
        assertThat(rank).isEqualTo(Rank.A);
    }

    @DisplayName("유효하지 않은 Rank 텍스트 입력 시 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenInvalidRankText() {
        assertThatThrownBy(() -> Rank.from("q"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 rank입니다.");
    }
}