package domain.board;

import static org.assertj.core.api.Assertions.*;

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

	@DisplayName("Board 저장, 불러오기, 삭제 테스트")
	@Test
	public void boardTest() throws SQLException {
		boardDao.clearBoardDb();

		Board board = new RookBoard().create();
		boardDao.saveBoard(board);

		Board newBoard = boardDao.loadBoard();
		assertThat(newBoard.calculateTeamScore(Team.WHITE)).isEqualTo(10);

		boardDao.clearBoardDb();
	}

	@DisplayName("Turn 저장, 불러오기, 삭제 테스트")
	@Test
	public void turnTest() throws SQLException {
		boardDao.clearTurn();

		boardDao.saveTurn(Team.BLACK);
		Team turn = boardDao.loadTurn();

		assertThat(turn).isEqualTo(Team.BLACK);
		boardDao.clearTurn();
	}
}
