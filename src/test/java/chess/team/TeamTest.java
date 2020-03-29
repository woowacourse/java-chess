package chess.team;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TeamTest {

	@DisplayName("팀의 정보를 제대로 가져오는지 확인")
	@ParameterizedTest
	@CsvSource(value = {"true,BLACK", "false,WHITE"})
	void of(boolean team, Team expect) {
		assertThat(Team.of(team)).isEqualTo(expect);
	}

	@DisplayName("팀이 블랙인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"BLACK,true", "WHITE,false"})
	void isBlack(Team team, boolean expect) {
		assertThat(team.isBlack()).isEqualTo(expect);
	}
}