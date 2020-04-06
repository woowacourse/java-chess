package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.state.Score;

class StatusTest {
	@Test
	@DisplayName("Status가 정상적으로 Winner를 리턴하는지 테스트합니다.")
	void winnerTest() {
		Status status = new Status(Score.of(20), Score.of(15));
		assertThat(status.winner()).isEqualTo(Team.WHITE);
	}

	@Test
	@DisplayName("Status가 정상적으로 Winner를 리턴하는지 테스트합니다.")
	void winnerTest2() {
		Status status = new Status(Score.of(15), Score.of(20));
		assertThat(status.winner()).isEqualTo(Team.BLACK);
	}

	@Test
	@DisplayName("Status가 정상적으로 Winner를 리턴하는지 테스트합니다.")
	void drawTest() {
		Status status = new Status(Score.of(20), Score.of(20));
		assertThat(status.winner()).isEqualTo(Team.NONE);
	}
}