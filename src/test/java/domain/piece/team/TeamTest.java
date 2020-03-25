package domain.piece.team;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TeamTest {
	@DisplayName("팀 생성")
	@Test
	void constructor_CreateTeam_Success() {
		assertThat(Team.BLACK).isInstanceOf(Team.class);
	}

	@DisplayName("현재 턴이 들어오면 다음 턴을 반환")
	@ParameterizedTest
	@CsvSource({"WHITE, BLACK", "BLACK, WHITE"})
	void changeTurn_GivenNowTurn_ReturnExpectedTurn(Team nowTurn, Team expectedTurn) {
		assertThat(Team.changeTurn(nowTurn)).isEqualTo(expectedTurn);
	}
}
