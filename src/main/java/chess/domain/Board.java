package chess.domain;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Piece;

public class Board {

	private static final String EMPTY_PIECE_IN_SOURCE_ERROR_MESSAGE = "source 위치에 조작할 수 있는 말이 없습니다.";
	private static final String SOURCE_TEAM_INCORRECT_ERROR_MESSAGE = "다른 팀의 말을 조작할 수 없습니다.";
	private static final String SAME_TEAM_IN_TARGET_ERROR_MESSAGE = "같은 팀의 말의 위치로 이동할 수 없습니다.";
	private static final String CANNOT_MOVE_TO_TARGET_DIRECTION_ERROR_MESSAGE = "말이 해당 방향으로 이동할 수 없습니다.";
	private static final String CANNOT_MOVE_THROUGH_OBSTACLE_ERROR_MESSAGE = "말이 이동하려는 방향에 장애물이 있습니다.";
	private static final String CANNOT_MOVE_PAWN_DIAGONAL_ERROR_MESSAGE = "폰은 적이 존재할 때만 대각선으로 이동할 수 있습니다.";

	private final Map<Position, Piece> board;

	private Board(Map<Position, Piece> board) {
		this.board = board;
	}

	public static Board empty() {
		return new Board(new HashMap<>());
	}

	public static Board create() {
		Map<Position, Piece> board = BoardMaker.setPieces();
		return new Board(board);
	}

	public void movePiece(final Team team, final Position source, final Position target) {
		checkIsMovable(team, source, target);
		Piece piece = board.get(source);
		board.put(target, piece);
		board.put(source, Piece.empty());
	}

	public void checkIsMovable(final Team team, final Position source, final Position target) {
		checkIsSourceEmpty(source);
		checkIsSourceCorrectTeam(team, source);
		checkIsSameTeamExistOnTarget(source, target);
		checkIsMovableDirection(source, target);
		checkIsThereAnyObstacle(source, target);
		checkIsSourcePawnMovingProperDiagonal(source, target);
	}

	private void checkIsSourceEmpty(final Position source) {
		Piece piece = board.get(source);
		if (piece.isGivenTeam(Team.EMPTY)) {
			throw new IllegalArgumentException(EMPTY_PIECE_IN_SOURCE_ERROR_MESSAGE);
		}
	}

	private void checkIsSourceCorrectTeam(final Team team, final Position source) {
		Piece piece = board.get(source);
		if (!piece.isGivenTeam(team)) {
			throw new IllegalArgumentException(SOURCE_TEAM_INCORRECT_ERROR_MESSAGE);
		}
	}

	private void checkIsSameTeamExistOnTarget(final Position source, final Position target) {
		Piece sourcePiece = board.get(source);
		Piece targetPiece = board.get(target);
		if (sourcePiece.getTeam() == targetPiece.getTeam()) {
			throw new IllegalArgumentException(SAME_TEAM_IN_TARGET_ERROR_MESSAGE);
		}
	}

	private void checkIsMovableDirection(final Position source, final Position target) {
		RelativePosition relativePosition = RelativePosition.of(source, target);
		Piece piece = board.get(source);
		if (!piece.isMobile(relativePosition)) {
			throw new IllegalArgumentException(CANNOT_MOVE_TO_TARGET_DIRECTION_ERROR_MESSAGE);
		}
	}

	private void checkIsThereAnyObstacle(final Position source, final Position target) {
		Piece piece = board.get(source);
		if (!piece.isKnight() && hasObstacle(source, target)) {
			throw new IllegalArgumentException(CANNOT_MOVE_THROUGH_OBSTACLE_ERROR_MESSAGE);
		}
	}

	private boolean hasObstacle(final Position source, final Position target) {
		RelativePosition relativePosition = RelativePosition.of(source, target);
		RelativePosition unitPosition = relativePosition.getGcdDivided();
		Position currentPosition = source.move(unitPosition);
		while (!currentPosition.equals(target)) {
			if (isPieceExists(currentPosition)) {
				return true;
			}
			currentPosition = currentPosition.move(unitPosition);
		}
		return false;
	}

	private boolean isPieceExists(final Position position) {
		return !board.get(position).isEmpty();
	}

	private void checkIsSourcePawnMovingProperDiagonal(final Position source, final Position target) {
		Piece sourcePiece = board.get(source);
		Piece targetPiece = board.get(target);
		RelativePosition relativePosition = RelativePosition.of(source, target);
		if (sourcePiece.isPawn() && relativePosition.isDiagonal() && targetPiece.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_MOVE_PAWN_DIAGONAL_ERROR_MESSAGE);
		}
	}

	public Map<Position, Piece> getBoard() {
		return board;
	}
}
