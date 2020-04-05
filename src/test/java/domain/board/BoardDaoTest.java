package domain.board;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.board.fixture.KingBoard;
import domain.piece.Rook;
import domain.piece.position.Position;
import domain.piece.team.Team;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class BoardDaoTest {
	private BoardDao boardDao;

	@BeforeEach
	void setUp() {
		boardDao = new BoardDao();
	}

	@Test
	public void connection() {
		Connection con = boardDao.getConnection();
		assertNotNull(con);
	}

	@Test
	public void save() throws SQLException {
		// Board board = new KingBoard().create();
		// boardDao.saveBoard(board);
	}
}
