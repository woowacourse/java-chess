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
	private Team team;
	private BoardDao boardDao;

	public WebService() {
		board = BoardFactory.create();
		team = Team.WHITE;
		boardDao = new BoardDao();
	}

	public List<String> showAllPieces() {
		return board.showAllPieces();
	}

	public double calculateTeamScore(Team team) {
		return board.calculateTeamScore(team);
	}

	public void move(String source, String target) {
		board.move(source, target, team);
		team = Team.changeTurn(team);
	}

	public String getTurn() {
		return team.getName() + "팀의 차례입니다!";
	}

	public String findWinner() {
		Team winner = board.findWinner();
		return winner.getName();
	}

	public void clearBoardDb() throws SQLException {
		boardDao.clearBoardDb();
	}

	public void saveGame() throws SQLException {
		boardDao.saveBoard(board);
	}

	public boolean isKingDead() {
		return board.isKingDead();
	}

	public void loadGame() throws SQLException {
		board = boardDao.loadBoard();
	}
}
