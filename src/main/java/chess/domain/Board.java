package chess.domain;

import java.util.Collections;
import java.util.Map;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class Board {

	private static final String EMPTY_PIECE_IN_SOURCE_ERROR_MESSAGE = "source 위치에 조작할 수 있는 말이 없습니다.";
	private static final String SOURCE_TEAM_INCORRECT_ERROR_MESSAGE = "다른 팀의 말을 조작할 수 없습니다.";
	private static final String SAME_TEAM_IN_TARGET_ERROR_MESSAGE = "같은 팀의 말의 위치로 이동할 수 없습니다.";
	private static final String CANNOT_MOVE_TO_TARGET_DIRECTION_ERROR_MESSAGE = "말이 해당 방향으로 이동할 수 없습니다.";
	private static final String CANNOT_MOVE_THROUGH_OBSTACLE_ERROR_MESSAGE = "말이 이동하려는 방향에 장애물이 있습니다.";
	private static final String CANNOT_MOVE_PAWN_VERTICAL_ERROR_MESSAGE = "폰은 적을 수직 방향으로 공격할 수 없습니다.";
	private static final String CANNOT_MOVE_PAWN_DIAGONAL_ERROR_MESSAGE = "폰은 적이 존재할 때만 대각선으로 이동할 수 있습니다.";
	private static final int INITIAL_BLACK_PAWN_ROW = 6;
	private static final int INITIAL_WHITE_PAWN_ROW = 1;

	private final Map<Position, Piece> board;

	private Board(Map<Position, Piece> board) {
		this.board = board;
	}

	public static Board create() {
		Map<Position, Piece> board = BoardMaker.setPieces();
		return new Board(board);
	}

	public void movePiece(final Team team, final Position source, final Position target) {
		checkIsMovable(team, source, target);
		Piece piece = board.get(source);
		if (isMovingInitialPawn(piece, team, source)) {
			board.put(target, new Pawn(team));
			board.put(source, new Empty());
			return;
		}
		board.put(target, piece);
		board.put(source, new Empty());
	}

	public void checkIsMovable(final Team team, final Position source, final Position target) {
		checkIsSourceEmpty(source);
		checkIsSourceCorrectTeam(team, source);
		checkIsSameTeamExistOnTarget(source, target);
		checkIsMovableDirection(source, target);
		checkIsThereAnyObstacle(source, target);
		checkIsSourcePawnTargetingForwardEnemy(source, target);
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
		if (isSameTeam(sourcePiece, targetPiece)) {
			System.out.println("srcTeam.team, White, Black, Empty = " + sourcePiece.isGivenTeam(Team.WHITE) + " " + sourcePiece.isGivenTeam(Team.BLACK) + " " + sourcePiece.isGivenTeam(Team.EMPTY));
			System.out.println("dstTeam.team, White, Black, Empty = " + targetPiece.isGivenTeam(Team.WHITE) + " " + targetPiece.isGivenTeam(Team.BLACK) + " " + targetPiece.isGivenTeam(Team.EMPTY));
			throw new IllegalArgumentException(SAME_TEAM_IN_TARGET_ERROR_MESSAGE);
		}
	}

	private boolean isSameTeam(final Piece sourcePiece, final Piece targetPiece) {
		return (sourcePiece.isGivenTeam(Team.WHITE) && targetPiece.isGivenTeam(Team.WHITE))
			|| (sourcePiece.isGivenTeam(Team.BLACK) && targetPiece.isGivenTeam(Team.BLACK));
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
		if (!piece.isGivenType(PieceType.KNIGHT) && hasObstacle(source, target)) {
			throw new IllegalArgumentException(CANNOT_MOVE_THROUGH_OBSTACLE_ERROR_MESSAGE);
		}
	}

	private boolean hasObstacle(final Position source, final Position target) {
		RelativePosition relativePosition = RelativePosition.of(source, target);
		RelativePosition unitPosition = relativePosition.getGcdDivided();
		Position currentPosition = source.move(unitPosition);
		while (!currentPosition.equals(target)) {
			if (doesPieceExists(currentPosition)) {
				return true;
			}
			currentPosition = currentPosition.move(unitPosition);
		}
		return false;
	}

	private boolean doesPieceExists(final Position position) {
		Piece piece = board.get(position);
		return !piece.isGivenType(PieceType.EMPTY);
	}

	private void checkIsSourcePawnTargetingForwardEnemy(final Position source, final Position target) {
		Piece sourcePiece = board.get(source);
		Piece targetPiece = board.get(target);
		RelativePosition relativePosition = RelativePosition.of(source, target);
		if (isPawn(sourcePiece) && relativePosition.isVertical() && isDifferentTeam(sourcePiece, targetPiece)) {
			throw new IllegalArgumentException(CANNOT_MOVE_PAWN_VERTICAL_ERROR_MESSAGE);
		}
	}

	private boolean isDifferentTeam(final Piece sourcePiece, final Piece targetPiece) {
		return (sourcePiece.isGivenTeam(Team.WHITE) && targetPiece.isGivenTeam(Team.BLACK))
			|| (sourcePiece.isGivenTeam(Team.BLACK) && targetPiece.isGivenTeam(Team.WHITE));
	}

	private void checkIsSourcePawnMovingProperDiagonal(final Position source, final Position target) {
		Piece sourcePiece = board.get(source);
		Piece targetPiece = board.get(target);
		RelativePosition relativePosition = RelativePosition.of(source, target);
		if (isPawn(sourcePiece) && relativePosition.isDiagonal() && targetPiece.isGivenType(PieceType.EMPTY)) {
			throw new IllegalArgumentException(CANNOT_MOVE_PAWN_DIAGONAL_ERROR_MESSAGE);
		}
	}

	private boolean isPawn(final Piece piece) {
		return piece.isGivenType(PieceType.INITIAL_PAWN) || piece.isGivenType(PieceType.PAWN);
	}

	private boolean isMovingInitialPawn(final Piece piece, final Team team, final Position source) {
		return piece.isGivenType(PieceType.INITIAL_PAWN) && isInitialPawnPosition(team, source);
	}

	private boolean isInitialPawnPosition(final Team team, final Position position) {
		if (team == Team.BLACK && position.getRow() == INITIAL_BLACK_PAWN_ROW) {
			return true;
		}
		return team == Team.WHITE && position.getRow() == INITIAL_WHITE_PAWN_ROW;
	}

	public boolean isKingPosition(final Position position) {
		return board.get(position)
			.isGivenType(PieceType.KING);
	}

	public Map<Position, Piece> getBoard() {
		return Collections.unmodifiableMap(board);
	}
}
