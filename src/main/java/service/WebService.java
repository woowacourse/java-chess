package service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	private static Map<String, String> unicodeConverter = new HashMap<>();

	static {
		unicodeConverter.put("k", "♔");
		unicodeConverter.put("q", "♕");
		unicodeConverter.put("r", "♖");
		unicodeConverter.put("b", "♗");
		unicodeConverter.put("n", "♘");
		unicodeConverter.put("p", "♙");

		unicodeConverter.put("K", "♚");
		unicodeConverter.put("Q", "♛");
		unicodeConverter.put("R", "♜");
		unicodeConverter.put("B", "♝");
		unicodeConverter.put("N", "♞");
		unicodeConverter.put("P", "♟");

		unicodeConverter.put("", "");
	}

	public WebService() {
		board = BoardFactory.create();
		turn = Team.WHITE;
		boardDao = new BoardDao();
	}

	public void initialize() {
		board = BoardFactory.create();
		turn = Team.WHITE;
		boardDao = new BoardDao();
	}

	public List<String> showAllPieces() {
		return board.showAllPieces().stream()
			.map(s -> s = unicodeConverter.get(s))
			.collect(Collectors.toList());
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
