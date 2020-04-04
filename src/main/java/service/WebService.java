package service;

import domain.board.Board;
import domain.piece.team.Team;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class WebService {
	private Team team = Team.WHITE;

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
}
