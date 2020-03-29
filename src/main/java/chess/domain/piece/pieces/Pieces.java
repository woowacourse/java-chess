package chess.domain.piece.pieces;

import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;

import java.util.List;

public class Pieces {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "잘못된 위치를 입력하셨습니다.";
	private static final int DEFAULT_KING_COUNT = 2;

	private final List<Piece> pieces;

	// package accessed
	Pieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public void move(Position start, Position end, Color color) {
		Piece piece = findBy(start, color);

		Positions movablePositions = piece.createMovablePositions(pieces);

		if (!movablePositions.contains(end)) {
			throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
		}

		Piece removingPiece = pieces.stream()
				.filter(findPiece -> findPiece.isSamePosition(end))
				.findFirst()
				.orElseGet(Blank::new);
		pieces.remove(removingPiece);

		piece.move(end);
	}

	public Piece findBy(Position start) {
		return pieces.stream()
				.filter(piece -> piece.isSamePosition(start))
				.findFirst()
				.orElseGet(Blank::new);
	}

	private Piece findBy(Position start, Color color) {
		Piece piece = findBy(start);
		if (piece.isSameColor(color)) {
			return piece;
		}
		throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
	}

	public List<Piece> getPieces() {
		return pieces;
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
}