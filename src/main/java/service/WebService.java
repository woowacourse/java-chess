package service;

import java.sql.SQLException;
import java.util.List;

import domain.board.Board;
import domain.board.BoardDao;
import domain.board.BoardFactory;
import domain.piece.team.Team;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class WebService {
	private Board board;
	private Team turn;
	private BoardDao boardDao;

	public WebService() {
		board = BoardFactory.create();
		turn = Team.WHITE;
		boardDao = new BoardDao();
	}

	public List<String> showAllPieces() {
		return board.showAllPieces();
	}

	public double calculateTeamScore(Team team) {
		return board.calculateTeamScore(team);
	}

	public void move(String source, String target) {
		board.move(source, target, turn);
		turn = Team.changeTurn(turn);
	}

	public String getTurn() {
		return turn.getName() + "팀의 차례입니다!";
	}

	public String findWinner() {
		Team winner = board.findWinner();
		return winner.getName();
	}

	public void clearDb() throws SQLException {
		boardDao.clearBoardDb();
		boardDao.clearTurn();
	}

	public void saveGame() throws SQLException {
		boardDao.saveBoard(board);
		boardDao.saveTurn(turn);
	}

	public boolean isKingDead() {
		return board.isKingDead();
	}

	public void loadGame() throws SQLException {
		board = boardDao.loadBoard();
	}
}
