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
		turnInfoDAO = new TurnInfoDAO();
	}

	@AfterEach
	void tearDown() {
		turnInfoDAO.truncate();
	}

	@Test
	void create() {
		assertThat(new TurnInfoDAO()).isInstanceOf(TurnInfoDAO.class);
	}

	@Test
	void initialize() {
		turnInfoDAO.initialize("1", Team.WHITE);

		assertThat(turnInfoDAO.findCurrent("1")).isEqualTo(Team.WHITE);
	}

	@Test
	void updateNext() {
		turnInfoDAO.initialize("1", Team.WHITE);
		turnInfoDAO.updateNext("1");

		assertThat(turnInfoDAO.findCurrent("1")).isEqualTo(Team.BLACK);
	}
}
