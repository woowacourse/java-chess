package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeamTest {

	@Test
	@DisplayName("White team이 Black team으로 바뀌어아 한다.")
	void alternateTest1() {
		Team team = Team.WHITE;
		Team changedTeam = team.alternate();
		assertThat(changedTeam).isEqualTo(Team.BLACK);
	}

	@Test
	@DisplayName("Black team이 White team으로 바뀌어아 한다.")
	void alternateTest2() {
		Team team = Team.BLACK;
		Team changedTeam = team.alternate();
		assertThat(changedTeam).isEqualTo(Team.WHITE);
	}

	@Test
	@DisplayName("Empty team이 Empty team으로 유지되어야 한다.")
	void alternateTest3() {
		Team team = Team.EMPTY;
		Team changedTeam = team.alternate();
		assertThat(changedTeam).isEqualTo(Team.EMPTY);
	}
}
