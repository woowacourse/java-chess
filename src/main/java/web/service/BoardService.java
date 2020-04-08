package web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import domain.Score;
import domain.board.BoardGame;
import domain.board.Rank;
import domain.command.MoveCommand;
import domain.piece.team.Team;
import web.dao.BoardDao;
import web.dto.ChessGameDto;
import web.dto.MoveCommandDto;

public class BoardService {
	private BoardDao boardDAO;
	private BoardGame boardGame;
	private String title;
	private Team turn;

	public BoardService() {
		boardGame = new BoardGame();
		boardDAO = BoardDao.getInstance();
		turn = Team.WHITE;
	}

	public ChessGameDto createGame(String title) {
		this.title = title;
		List<Rank> board = boardGame.getReverse();
		Map<Team, Double> score = Score.calculateScore(board);
		return new ChessGameDto(title, board, turn.getName(), score);
	}

	public ChessGameDto move(MoveCommandDto inputMoveCommand) {
		MoveCommand moveCommand = new MoveCommand(inputMoveCommand.getCommand());
		boardGame.move(moveCommand, turn);
		turn = Team.changeTurn(turn);
		List<Rank> board = boardGame.getReverse();
		Map<Team, Double> score = Score.calculateScore(board);
		return new ChessGameDto(title, board, turn.getName(), score);
	}

	public void addBoard() throws SQLException {
		List<Rank> board = boardGame.getReverse();
		Map<Team, Double> score = Score.calculateScore(board);
		ChessGameDto chessGameDto = new ChessGameDto(title, board, turn.getName(), score);
		boardDAO.addBoard(chessGameDto);
	}
}
