package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TeamTest {

	@DisplayName("팀에 속하지 않는 경우 false")
	@ParameterizedTest
	@CsvSource({"BLACK,true", "WHITE,true", "NONE,false"})
	void isNotBlankTest(Team team, boolean expected) {
		boolean actual = team.isNotBlank();
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("팀이 흑팀인 경우에만 true 반환")
	@ParameterizedTest
	@CsvSource({"BLACK,true", "WHITE,false", "NONE,false"})
	void isBlackTest(Team team, boolean expected) {
		boolean actual = team.isBlack();
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("팀이 백팀인 경우에만 true 반환")
	@ParameterizedTest
	@CsvSource({"BLACK,false", "WHITE,true", "NONE,false"})
	void isWhite(Team team, boolean expected) {
		boolean actual = team.isWhite();
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("흑팀인 경우 백팀 반환, 백팀인 경우 흑팀 반환")
	@ParameterizedTest
	@CsvSource({"BLACK,WHITE", "WHITE,BLACK"})
	void getOppositeTeam(Team team, Team expected) {
		Team actual = team.getOppositeTeam();
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("어느 팀도 속하지 않은 경우, 반대 팀 구하려하면 USO 예외 발생")
	@Test
	void getOppositeTeam_exception_none_team() {
		Team team = Team.NONE;
		assertThatThrownBy(team::getOppositeTeam)
			.isInstanceOf(UnsupportedOperationException.class);
	}
}