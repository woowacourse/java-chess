package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {
    @DisplayName("0부터 시작하는 행번호로 Rank를 찾을 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"0, EIGHT", "1, SEVEN", "2, SIX", "3, FIVE", "4, FOUR", "5, THREE", "6, TWO", "7, ONE"})
    void should_FindRank_When_RowNumberGiven(int rowNumber, Rank rank) {
        assertThat(Rank.from(rowNumber)).isEqualTo(rank);
    }

    @DisplayName("행번호에 해당하는 Rank가 없을 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 9, 10})
    void should_ThrowNoSuchElementException_When_WrongRowNumberIsGiven(int invalidRowNumber) {
        assertThatThrownBy(() -> Rank.from(invalidRowNumber))
                .isInstanceOf(NoSuchElementException.class);
    }
}
