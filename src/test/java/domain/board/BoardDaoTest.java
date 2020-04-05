package domain.board;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.fixture.KingBoard;
import domain.board.fixture.RookBoard;
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

	@DisplayName("Board에 데이터 추가")
	@Test
	public void insertTest() throws SQLException {
		Board board = new RookBoard().create();
		boardDao.saveBoard(board);
	}

	@DisplayName("Board에 데이터 삭제")
	@Test
	public void deleteTest() throws SQLException {
		boardDao.clearBoardDb();
	}
}
