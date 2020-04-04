package chess.domain.piece.pieces;

import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;
import chess.domain.util.WrongPositionException;

import java.util.List;

public class Pieces {
	private final List<Piece> pieces;

	// package accessed
	Pieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public void move(Position start, Position end, Color color) {
		Piece piece = findByPosition(start, color);

		Positions movablePositions = piece.findMovablePositions(pieces);

		if (!movablePositions.contains(end)) {
			throw new WrongPositionException();
		}

		pieces.removeIf(findPiece -> findPiece.isSamePosition(end));

		piece.move(end);
	}

	public Piece findByPosition(Position start) {
		return pieces.stream()
				.filter(piece -> piece.isSamePosition(start))
				.findFirst()
				.orElseGet(Blank::new);
	}

	private Piece findByPosition(Position start, Color color) {
		Piece piece = findByPosition(start);
		if (piece.isSameColor(color)) {
			return piece;
		}
		throw new WrongPositionException(start.toString());
	}

	public Positions findMovablePositions(Position position, Color color) {
		Piece piece = findByPosition(position, color);

		return piece.findMovablePositions(pieces);
	}

	public boolean isKingDead() {
		int kingCount = (int) pieces.stream()
				.filter(Piece::isKing)
				.count();
		return kingCount != 2;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public Color getAliveKingColor() {
		return pieces.stream()
				.filter(Piece::isKing)
				.map(Piece::getColor)
				.findFirst()
				.orElseThrow(() -> new UnsupportedOperationException("현재상황에서 사용할 수 없는 메서드입니다."));
	}
}