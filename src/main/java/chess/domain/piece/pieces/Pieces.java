package chess.domain.piece.pieces;

import chess.domain.board.position.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Pieces {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "잘못된 위치를 입력하셨습니다.";
	private final List<Piece> pieces;

	// package accessed
	Pieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public void move(Position start, Position end, Color color) {
		Piece piece = findBy(start, color).orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE));

		Set<Position> movablePositions = piece.createMovablePositions(pieces);

		if (!movablePositions.contains(end)) {
			throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
		}

		Piece removingPiece = pieces.stream()
				.filter(findPiece -> findPiece.isSamePosition(end))
				.findFirst()
				.orElseGet(Blank::new);
		pieces.remove(removingPiece);

		piece.changePosition(end);
	}

	public Optional<Piece> findBy(Position start) {
		for (Piece piece : pieces) { // TODO: 2020/03/26 refactor
			if (piece.isSamePosition(start)) {
				return Optional.ofNullable(piece);
			}
		}
		return Optional.empty();
	}

	public Optional<Piece> findBy(Position start, Color color) {
		Piece piece = findBy(start).orElseGet(Blank::new);
		if (piece.isSameColor(color)) {
			return Optional.ofNullable(piece);
		}
		return Optional.empty();
	}

	public List<Piece> getPieces() {
		return pieces;
	}
}