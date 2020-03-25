package domain.piece.team;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TeamTest {
	@Test
	void constructor_CreateTeam_Success() {
		assertThat(Team.BLACK).isInstanceOf(Team.class);
	}
}
