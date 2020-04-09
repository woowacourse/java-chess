package chess.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.dao.BoardDao;
import chess.dao.TurnDao;
import chess.domain.GameManager;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.dto.PieceDto;

public class GameManagerService {
	private final BoardDao boardDao;
	private final TurnDao turnDao;

	public GameManagerService() {
		this.boardDao = new BoardDao();
		this.turnDao = new TurnDao();
	}

	public void move(Position targetPosition, Position destination) throws SQLException {
		Board board = new Board(boardDao.findAllPieces());
		Color turn = turnDao.findTurn();
		GameManager gameManager = new GameManager(board, turn);

		gameManager.move(targetPosition, destination);

		boardDao.editPieceByPosition(destination, boardDao.findPiece(targetPosition));
		boardDao.deletePieceByPosition(targetPosition);
		turnDao.editTurn(gameManager.getCurrentTurn());
	}

	public void resetGame() throws SQLException {
		boardDao.deleteAll();
		turnDao.editTurn(Color.WHITE);
		Board board = BoardFactory.create();
		List<PieceDto> pieces = board.getPieces().entrySet().stream()
			.map(x -> PieceDto.of(x.getKey(), x.getValue()))
			.collect(Collectors.toList());

		boardDao.addAllPieces(pieces);
	}

	public Board getBoard() throws SQLException {
		return new Board(boardDao.findAllPieces());
	}

	public Color getCurrentTurn() throws SQLException {
		return turnDao.findTurn();
	}

	public boolean isKingAlive() throws SQLException {
		Board board = new Board(boardDao.findAllPieces());
		Color turn = turnDao.findTurn();
		GameManager gameManager = new GameManager(board, turn);

		return gameManager.isKingAlive();
	}

	public Map<Color, Double> calculateEachScore() throws SQLException {
		Board board = new Board(boardDao.findAllPieces());
		Color turn = turnDao.findTurn();
		GameManager gameManager = new GameManager(board, turn);

		return gameManager.calculateEachScore();
	}
}
