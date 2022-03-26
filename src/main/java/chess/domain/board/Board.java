package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.position.UnitPosition;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;

public class Board {

	private static final String NO_PIECE = "해당 위치에 말이 없습니다.";
	private static final String PIECE_BLOCK = "가려는 위치 중간에 말이 존재합니다.";
	private static final String CANNOT_MOVE_SAME_COLOR = "아군이 있는 위치에 갈 수 없습니다.";
	private static final String PAWN_ONLY_DIAGONAL_CATCH = "폰은 본인 진행 방향 대각선에 있는 적만 잡을 수 있습니다.";

	private final Map<Position, Piece> pieces;

	public Board() {
		this.pieces = BoardInitializer.generate();
	}

	public Board(Map<Position, Piece> pieces) {
		this.pieces = new HashMap<>(pieces);
	}

	public Optional<Piece> findPiece(Position position) {
		return pieces.entrySet().stream()
			.filter(entry -> entry.getKey().equals(position))
			.map(Map.Entry::getValue)
			.findFirst();
	}

	public void movePiece(Position from, Position to) {
		Piece fromPiece = checkFromPieceEmpty(from);
		Direction direction = fromPiece.matchDirection(from, to);
		searchPiece(from, to, direction);
		checkTargetPosition(to, fromPiece, direction);

		move(from, to, fromPiece);
	}

	public boolean isWhite(Position position) {
		return checkFromPieceEmpty(position).isWhite();
	}

	private Piece checkFromPieceEmpty(Position from) {
		Optional<Piece> piece = findPiece(from);
		if (piece.isEmpty()) {
			throw new NoSuchElementException(NO_PIECE);
		}
		return piece.get();
	}

	private void searchPiece(Position from, Position to, Direction direction) {
		Position step = from.convert(new UnitPosition(0, 0));
		while (!step.equals(to)) {
			step = step.convert(direction.getUnitPosition());
			validateExistPiece(to, step);
		}
	}

	private void validateExistPiece(Position to, Position step) {
		if (findPiece(step).isPresent() && !step.equals(to)) {
			throw new IllegalArgumentException(PIECE_BLOCK);
		}
	}

	private void checkTargetPosition(Position to, Piece fromPiece, Direction direction) {
		Optional<Piece> piece;
		piece = findPiece(to);
		if (piece.isPresent()) {
			Piece toPiece = piece.get();
			validateSameColor(fromPiece, toPiece);
			checkPieceIsPawn(fromPiece, direction, toPiece);
		}
	}

	private void validateSameColor(Piece fromPiece, Piece toPiece) {
		if (fromPiece.isSameColor(toPiece)) {
			throw new IllegalArgumentException(CANNOT_MOVE_SAME_COLOR);
		}
	}

	private void checkPieceIsPawn(Piece fromPiece, Direction direction, Piece toPiece) {
		if (fromPiece.isPawn()) {
			validateDiagonalEnemy(fromPiece, toPiece, direction);
		}
	}

	private void validateDiagonalEnemy(Piece fromPiece, Piece toPiece, Direction direction) {
		if (!direction.isDiagonal() || fromPiece.isSameColor(toPiece)) {
			throw new IllegalArgumentException(PAWN_ONLY_DIAGONAL_CATCH);
		}
	}

	private void move(Position from, Position to, Piece piece) {
		this.pieces.remove(from);
		this.pieces.put(to, piece);
	}

	public Map<Position, Piece> getPieces() {
		return new HashMap<>(pieces);
	}
}
