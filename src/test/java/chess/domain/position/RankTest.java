package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {

    @DisplayName("문자열을 받아 알맞은 랭크를 반환한다.")
    @ParameterizedTest
    @CsvSource({"1,ONE", "2,TWO", "3,THREE", "4,FOUR", "5,FIVE", "6,SIX", "7,SEVEN", "8,EIGHT"})
    void 문자열을_받아_알맞은_랭크를_반환한다(String value, Rank rank) {
        assertThat(Rank.of(value)).isEqualTo(rank);
    }

    @DisplayName("잘못된 문자열이 들어온 경우 예외가 발생한다.")
    @Test
    void 잘못된_문자열이_들어온_경우_예외가_발생한다() {
        assertThatThrownBy(() -> Rank.of("9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 랭크입니다.");
    }
}
