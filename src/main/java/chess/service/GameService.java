package chess.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.game.dao.MySQLGameDAO;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.view.dto.responsedto.BoardDTO;
import chess.view.dto.responsedto.GameDTO;
import chess.view.dto.responsedto.ScoreDTO;

public class GameService {
	private MySQLGameDAO gameDAO;

	public GameService(MySQLGameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}

	public List<ScoreDTO> calculateScore() {
		Game game = null;
		try {
			game = gameDAO.findById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map<Team, Double> status = game.status();
		return status.entrySet().stream()
			.map(entry -> new ScoreDTO(entry.getKey().name().toLowerCase(), entry.getValue()))
			.collect(Collectors.toList());
	}

	public void startNewGame() {
		Game game = null;
		try {
			game = gameDAO.findById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		game.start();

		try {
			gameDAO.update(game);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<BoardDTO> findAllPiecesOnBoard() {
		Game game = null;
		try {
			game = gameDAO.findById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Board board = game.getBoard();
		Map<Position, Piece> pieces = board.getPieces();

		return pieces.entrySet().stream()
			.map(entry -> new BoardDTO(entry.getKey().toString(), entry.getValue().getSymbol()))
			.collect(Collectors.toList());
	}

	public void move(Position from, Position to) {
		Game game = null;
		try {
			game = gameDAO.findById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		game.move(from, to);
		try {
			gameDAO.update(game);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<BoardDTO> findChangedPiecesOnBoard(Position from, Position to) {
		Game game = null;
		try {
			game = gameDAO.findById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Board board = game.getBoard();
		List<BoardDTO> result = new ArrayList<>();
		result.add(new BoardDTO(from.toString(), board.findPiece(from).getSymbol()));
		result.add(new BoardDTO(to.toString(), board.findPiece(to).getSymbol()));
		return result;
	}

	public GameDTO getCurrentState() {
		Game game = null;
		try {
			game = gameDAO.findById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return GameDTO.of(game.getTurn(), game.getStateType());
	}

	public void endGame() {
		Game game = null;
		try {
			game = gameDAO.findById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		game.end();
		try {
			gameDAO.update(game);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getWinner() {
		Game game = null;
		try {
			game = gameDAO.findById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return game.findWinner().name().toLowerCase();
	}

	public boolean isNotFinish() {
		Game game = null;
		try {
			game = gameDAO.findById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return game.isNotEnd();
	}
}
