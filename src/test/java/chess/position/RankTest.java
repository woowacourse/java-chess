package chess.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RankTest {
	@DisplayName("Rank 객체값이 1-8 사이인지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"0", "9"})
	void invalidOfTest(String name) {
		assertThat(Rank.of(name)).isEqualTo(Rank.NONE);
	}

	@DisplayName("Rank.of() 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"1", "4", "8"})
	void ofTest(String name) {
		assertThat(Rank.of(name)).isInstanceOf(Rank.class);
	}

}
