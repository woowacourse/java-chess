package chess.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class Board {

	private static final String EMPTY_PIECE_IN_SOURCE_ERROR_MESSAGE = "source 위치에 조작할 수 있는 말이 없습니다.";
	private static final String SAME_TEAM_IN_TARGET_ERROR_MESSAGE = "같은 팀의 말의 위치로 이동할 수 없습니다.";
	private static final String CANNOT_MOVE_TO_TARGET_DIRECTION_ERROR_MESSAGE = "말이 해당 위치로 이동할 수 없습니다.";

	private final Map<Position, Piece> board;
	private Board(Map<Position, Piece> board) {
		this.board = board;
	}

	public static Board empty() {
		return new Board(new HashMap<>());
	}

	public static Board create() {
		HashMap<Position, Piece> board = new HashMap<>();
		List<Piece> whitePieces = new ArrayList<>(
			List.of(new Rook(Team.WHITE), new Knight(Team.WHITE), new Bishop(Team.WHITE), new Queen(Team.WHITE),
				new King(Team.WHITE), new Bishop(Team.WHITE), new Knight(Team.WHITE), new Rook(Team.WHITE))
		);
		List<Piece> blackPieces = new ArrayList<>(
			List.of(new Rook(Team.BLACK), new Knight(Team.BLACK), new Bishop(Team.BLACK), new Queen(Team.BLACK),
				new King(Team.BLACK), new Bishop(Team.BLACK), new Knight(Team.BLACK), new Rook(Team.BLACK))
		);

		for (int i = 0; i < 8; i++) {
			board.put(new Position(i, 0), whitePieces.get(i));
		}
		for (int i = 0; i < 8; i++) {
			board.put(new Position(i, 1), new Pawn(Team.WHITE));
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 2; j < 6; j++) {
				board.put(new Position(i, j), new Empty());
			}
		}
		for (int i = 0; i < 8; i++) {
			board.put(new Position(i, 6), new Pawn(Team.BLACK));
		}
		for (int i = 7; i >= 0; i--) {
			board.put(new Position(i, 7), blackPieces.get(i));
		}
		return new Board(board);
	}

	public void movePiece(Position source, Position target) {
		if (isEmpty(source)) {
			throw new IllegalArgumentException(EMPTY_PIECE_IN_SOURCE_ERROR_MESSAGE);
		}
		if (hasSameTeam(source, target)) {
			throw new IllegalArgumentException(SAME_TEAM_IN_TARGET_ERROR_MESSAGE);
		}
		if (isInvalidDirection(source, target)) {
			throw new IllegalArgumentException(CANNOT_MOVE_TO_TARGET_DIRECTION_ERROR_MESSAGE);
		}
		Piece piece = board.get(source);
		if (!piece.isKnight() && hasObstacle(source, target)) {
			throw new IllegalArgumentException(CANNOT_MOVE_TO_TARGET_DIRECTION_ERROR_MESSAGE);
		}
		RelativePosition relativePosition = RelativePosition.of(source, target);
		if (piece.isPawn() && relativePosition.isDiagonal() && isEmpty(target)) {
			throw new IllegalArgumentException(CANNOT_MOVE_TO_TARGET_DIRECTION_ERROR_MESSAGE);
		}
		board.put(target, piece);
		board.put(source, new Empty());
	}

	private boolean isEmpty(Position source) {
		return board.get(source).isEmpty();
	}

	private boolean hasSameTeam(Position source, Position target) {
		Piece sourcePiece = board.get(source);
		Piece targetPiece = board.get(target);
		if (sourcePiece.isBlack() && targetPiece.isBlack()) {
			return true;
		}
		return sourcePiece.isWhite() && targetPiece.isWhite();
	}

	private boolean isInvalidDirection(Position source, Position target) {
		RelativePosition relativePosition = RelativePosition.of(source, target);
		Piece piece = board.get(source);
		return !piece.isMobile(relativePosition);
	}

	private boolean hasObstacle(Position source, Position target) {
		RelativePosition relativePosition = RelativePosition.of(source, target);
		RelativePosition unitPosition = relativePosition.toUnit();
		Position currentPosition = source.move(unitPosition);
		while (!currentPosition.equals(target)) {
			if (!isEmpty(currentPosition)) {
				return true;
			}
			currentPosition = currentPosition.move(unitPosition);
		}
		return false;
	}

	public Map<Position, Piece> getBoard() {
		return board;
	}
}
