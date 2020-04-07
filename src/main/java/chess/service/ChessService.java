package chess.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import chess.dao.BoardDAO;
import chess.dao.TurnInfoDAO;
import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.MoveInfo;
import chess.domain.position.Position;

public class ChessService {
	private final BoardDAO boardDAO;
	private final TurnInfoDAO turnInfoDAO;

	public ChessService(BoardDAO boardDAO, TurnInfoDAO turnInfoDAO) {
		this.boardDAO = boardDAO;
		this.turnInfoDAO = turnInfoDAO;
	}

	public void initialize(String gameId) {
		if (boardDAO.findAll(gameId).isEmpty()) {
			for (Piece piece : BoardFactory.toList()) {
				boardDAO.addPiece(gameId, piece);
			}
			turnInfoDAO.initialize(gameId, Team.WHITE);
		}
	}

	public void move(String gameId, MoveInfo moveInfo) {
		Board board = Board.of(boardDAO.findAll(gameId));
		Position from = moveInfo.getFrom();
		Position to = moveInfo.getTo();

		board.verifyMove(from, to, turnInfoDAO.findCurrent(gameId));

		boardDAO.update(gameId, from, to);
		turnInfoDAO.updateNext(gameId);
	}

	public Map<String, String> getBoard(String gameId) {
		return boardDAO.findAll(gameId)
			.stream()
			.collect(Collectors.toMap(
				piece -> piece.getPosition().getName(),
				Piece::getSymbol
			));
	}

	public Map<String, String> getResult(String gameId) {
		Map<String, String> result = new HashMap<>();
		Status status = Status.of(boardDAO.findAll(gameId));

		String whiteScore = String.valueOf(status.toMap().get(Team.WHITE));
		String blackScore = String.valueOf(status.toMap().get(Team.BLACK));
		String winner = String.valueOf(status.getWinner().getName());

		result.put("whiteScore", whiteScore);
		result.put("blackScore", blackScore);
		result.put("winner", winner);

		return result;
	}
}
