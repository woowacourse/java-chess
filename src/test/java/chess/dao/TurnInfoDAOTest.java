package chess.dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Team;

public class TurnInfoDAOTest {
	private TurnInfoDAO turnInfoDAO;

	@BeforeEach
	void setUp() {
		turnInfoDAO = new TurnInfoDAO("1");
	}

	@AfterEach
	void tearDown() {
		turnInfoDAO.truncate();
	}

	@Test
	void create() {
		assertThat(new TurnInfoDAO("1")).isInstanceOf(TurnInfoDAO.class);
	}

	@Test
	void initialize() {
		turnInfoDAO.initialize(Team.WHITE);

		assertThat(turnInfoDAO.findCurrent()).isEqualTo(Team.WHITE);
	}

	@Test
	void updateNext() {
		turnInfoDAO.initialize(Team.WHITE);
		turnInfoDAO.updateNext();

		assertThat(turnInfoDAO.findCurrent()).isEqualTo(Team.BLACK);
	}
}
