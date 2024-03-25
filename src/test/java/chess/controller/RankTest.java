package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("문자에 해당하는 행 번호를 반환한다.")
    void findRank() {
        String input = "1";
        assertThat(Rank.findRank(input)).isEqualTo(1);
    }

    @Test
    @DisplayName("존재하지 않는 행문자열을 입력하면 예외가 발생한다.")
    void findRankByInvalidInput() {
        String input = "aa";
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Rank.findRank(input))
                .withMessage("올바르지 않은 행입니다.");
    }
}
