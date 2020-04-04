package chess.domain.utils;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import chess.domain.Team;

public class NameUtilsTest {

	@Test
	void parseNameTest() {
		assertThat(NameUtils.parseName("k", Team.BLACK)).isEqualTo("K");
		assertThat(NameUtils.parseName("k", Team.WHITE)).isEqualTo("k");
	}
}
