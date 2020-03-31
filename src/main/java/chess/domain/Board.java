package chess.domain;

import java.util.List;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Board {
	private List<Piece> pieces;

	public Board(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public void move(Position source, Position target) {
		validateTargetPosition(target);

		Piece sourcePiece = findPieceBy(source);
		sourcePiece.move(target);
	}

	private void validateTargetPosition(Position target) {
		if (isPresent(target)) {
			throw new IllegalArgumentException("해당 위치에 말이 존재합니다.");
		}
	}

	public Piece findPieceBy(Position position) {
		return pieces.stream()
				.filter(piece -> piece.isSamePosition(position))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("해당 위치에 말이 없습니다."));
	}

	public boolean isPresent(Position position) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position));
	}
}
