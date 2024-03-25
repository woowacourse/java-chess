package chess.domain.piece;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import chess.domain.chessboard.attribute.Direction;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public abstract class SlidingPiece extends Piece {

	protected SlidingPiece(final Color color, final Position position) {
		super(color, position);
	}

	protected Set<Position> possiblePositionsTo(final Set<Direction> directions) {
		Set<Position> positions = new HashSet<>();
		for (final Direction direction : directions) {
			positions.addAll(positionsAfterSlideTo(direction));
		}
		return positions;
	}

	private Set<Position> positionsAfterSlideTo(final Direction direction) {
		Set<Position> positions = new HashSet<>();
		Optional<Position> next = position().moveTo(direction);
		while (next.isPresent()) {
			Position position = next.get();
			positions.add(position);
			next = position.moveTo(direction);
		}
		return positions;
	}
}
