package chess.domain.piece.pieces;

import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;

import java.util.List;

public class Pieces {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "유효한 입력이 아닙니다. 다시 입력해주세요";
	private static final int DEFAULT_KING_COUNT = 2;

	private final List<Piece> pieces;

	// package accessed
	Pieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public void move(Position start, Position end, Color color) {
		Piece piece = findBy(start, color);
		Positions movablePositions = piece.createMovablePositions(pieces);
		validateEndPositionIsMovable(end, movablePositions);

		Piece removingPiece = findBy(end);
		pieces.remove(removingPiece);

		piece.move(end);
	}

	public Piece findBy(Position position) {
		return pieces.stream()
				.filter(piece -> piece.isSamePosition(position))
				.findFirst()
				.orElseGet(Blank::new);
	}

	public Piece findBy(Position start, Color color) {
		Piece piece = findBy(start);
		if (piece.isSameColor(color)) {
			return piece;
		}
		throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
	}

	private void validateEndPositionIsMovable(Position end, Positions movablePositions) {
		if (!movablePositions.contains(end)) {
			throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
		}
	}

	public boolean isKingDead() {
		int kingCount = (int) pieces.stream()
				.filter(Piece::isKing)
				.count();
		return kingCount != DEFAULT_KING_COUNT;
	}

	public Color getAliveKingColor() {
		return pieces.stream()
				.filter(Piece::isKing)
				.map(Piece::getColor)
				.findFirst()
				.orElseThrow(() -> new UnsupportedOperationException("현재상황에서 사용할 수 없는 메서드입니다."));
	}

	public List<Piece> getPieces() {
		return pieces;
	}
}