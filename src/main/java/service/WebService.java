package service;

import java.sql.SQLException;

import domain.board.Board;
import domain.board.BoardDao;
import domain.piece.team.Team;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class WebService {
	private Team team = Team.WHITE;
	private BoardDao boardDao = new BoardDao();

	public void move(Board board, String source, String target) {
		board.move(source, target, team);
		team = Team.changeTurn(team);
	}

	public String getTurn() {
		return team.getName() + "팀의 차례입니다!";
	}

	public String findWinner(Board board) {
		Team winner = board.findWinner();
		return winner.getName();
	}

	public void saveGame(Board board) throws SQLException {
		boardDao.saveBoard(board);
	}
}
