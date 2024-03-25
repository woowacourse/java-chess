package chess.domain.chessboard.attribute;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {
	@DisplayName("0~7 범위를 벗어나는 행 번호를 입력하면 예외가 발생한다.")
	@ValueSource(ints = {-1, 8})
	@ParameterizedTest
	void constructor(int invalidRow) {
		assertThatThrownBy(() -> Rank.from(invalidRow))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("랭크는 1~8 사이로 입력해주세요: " + invalidRow);
	}

	@DisplayName("1~8 범위를 벗어나는 랭크를 입력하면 예외가 발생한다.")
	@ValueSource(strings = {"0", "9"})
	@ParameterizedTest
	void constructor(String invalidRank) {
		assertThatThrownBy(() -> Rank.from(invalidRank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("랭크는 1~8 사이로 입력해주세요: " + invalidRank);
	}
}
