package chess.service;

import java.util.Map;
import java.util.stream.Collectors;

import chess.dao.BoardDAO;
import chess.dao.TurnInfoDAO;
import chess.domain.Status;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.MoveInfo;
import chess.domain.position.Path;
import chess.domain.position.Position;

public class ChessService {
	private static final int TEAM_COUNT = 2;

	private final BoardDAO boardDAO;
	private final TurnInfoDAO turnInfoDAO;

	public ChessService(BoardDAO boardDAO, TurnInfoDAO turnInfoDAO) {
		this.boardDAO = boardDAO;
		this.turnInfoDAO = turnInfoDAO;
	}

	public void move(String gameId, MoveInfo moveInfo) {
		Position from = moveInfo.getFrom();
		Position to = moveInfo.getTo();

		validateMove(gameId, from, to, turnInfoDAO.findCurrent(gameId));

		boardDAO.update(gameId, from, to);
		turnInfoDAO.updateNext(gameId);
	}

	private void validateMove(String gameId, Position from, Position to, Team team) {

		Piece piece = boardDAO.findPieceBy(gameId, from);
		Piece target = boardDAO.findPieceBy(gameId, to);

		if (piece.isNotSameTeam(team)) {
			throw new IllegalArgumentException("아군 기물의 위치가 아닙니다.");
		}
		if (hasPieceIn(gameId, Path.of(from, to)) || piece.canNotMoveTo(target)) {
			throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
		}
	}

	private boolean hasPieceIn(String gameId, Path path) {
		return path.toList()
			.stream()
			.anyMatch(position -> boardDAO.findPieceBy(gameId, position).isObstacle());
	}

	public Map<String, String> getBoard(String gameId) {
		return boardDAO.findAll(gameId)
			.stream()
			.collect(Collectors.toMap(
				piece -> piece.getPosition().getName(),
				Piece::getSymbol
			));
	}

	public boolean isEnd(String gameId) {
		return boardDAO.findAll(gameId)
			.stream()
			.filter(Piece::hasToAlive)
			.count() < TEAM_COUNT;
	}

	public void initialize(String gameId) {
		if (boardDAO.findAll(gameId).isEmpty()) {
			for (Piece piece : BoardFactory.create().getBoard().values()) {
				boardDAO.addPiece(gameId, piece);
			}
			turnInfoDAO.initialize(gameId, Team.WHITE);
		}
	}

	public Status getStatus(String gameId) {
		return Status.of(boardDAO.findAll(gameId));
	}
}
