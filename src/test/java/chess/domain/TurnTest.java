package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {
	@Test
	@DisplayName("턴을 스위칭하는 메소드가 정상작동하는지 테스트합니다.")
	void switchTurnWhiteToBlack() {
		Turn turn = new Turn(Team.WHITE);
		turn.switchTurn();
		assertThat(turn.getTeam()).isEqualTo(Team.BLACK);
	}

	@Test
	@DisplayName("턴을 스위칭하는 메소드가 반대의 경우도정상작동하는지 테스트합니다.")
	void switchTurnBlackToWhite() {
		Turn turn = new Turn(Team.BLACK);
		turn.switchTurn();
		assertThat(turn.getTeam()).isEqualTo(Team.WHITE);
	}

	@Test
	void isSameTeam() {
		Turn turn = new Turn(Team.BLACK);
		turn.isSameTeam(Team.WHITE);
	}
}