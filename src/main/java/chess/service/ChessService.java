package chess.service;

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
		if (boardDAO.findBoardBy(gameId).isEmpty()) {
			for (Piece piece : BoardFactory.toList()) {
				boardDAO.addPiece(gameId, piece);
			}
			turnInfoDAO.initialize(gameId, Team.WHITE);
		}
	}

	public void move(String gameId, MoveInfo moveInfo) {
		Board board = boardDAO.findBoardBy(gameId);
		Position from = moveInfo.getFrom();
		Position to = moveInfo.getTo();

		board.verifyMove(from, to, turnInfoDAO.findCurrent(gameId));

		boardDAO.update(gameId, from, to);
		turnInfoDAO.updateNext(gameId);
	}

	public Board getBoard(String gameId) {
		return boardDAO.findBoardBy(gameId);
	}

	public Status getResult(String gameId) {
		return Status.of(boardDAO.findBoardBy(gameId));
	}
}
