package domain.board;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.fixture.RookBoard;
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

	@DisplayName("Board 저장, 불러오기, 삭제 테스트")
	@Test
	public void loadBoardTest() throws SQLException {
		boardDao.clearBoardDb();

		Board board = new RookBoard().create();
		boardDao.saveBoard(board);

		Board newBoard = boardDao.loadBoard();
		assertThat(board.calculateTeamScore(Team.WHITE)).isEqualTo(10);

		boardDao.clearBoardDb();
	}

	@DisplayName("현재의 공격 순서 저장")
	@Test
	public void saveTurnTest() throws SQLException {
		boardDao.saveTurn(Team.BLACK);
	}

	@DisplayName("게임 저장 버튼을 누르면 Turn의 데이터 삭제")
	@Test
	void clearTurnTest() throws SQLException {
		boardDao.clearTurn();
	}
}
