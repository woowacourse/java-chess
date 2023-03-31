package chess.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Team;

class FinalResultTest {

	@Test
	@DisplayName("생성 시 입력된 승자를 반환해야 한다.")
	void finalResultConstructionTest() {
		FinalResult result = new FinalResult(Team.EMPTY);

		assertThat(result.getWinner()).isEqualTo(Team.EMPTY);
	}
}
