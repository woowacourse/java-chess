package chess.domain.piece;

import static chess.domain.piece.Color.WHITE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chess.domain.coordinates.Coordinates;
import chess.domain.coordinates.Direction;

public abstract class AbstractPiece implements Piece {
	protected final Set<Direction> movableDirections;
	protected final String name;
	protected final Color color;
	protected final double score;

	public AbstractPiece(List<Direction> movableDirections, String name, Color color, double score) {
		this.movableDirections = new HashSet<>(movableDirections);
		this.name = name;
		this.color = color;
		this.score = score;
	}

	@Override
	public abstract List<Coordinates> findMovableCoordinates(Coordinates from, Coordinates to);

	@Override
	public boolean isKing() {
		return false;
	}

	@Override
	public boolean isPawn() {
		return false;
	}

	@Override
	public boolean isAlly(Piece that) {
		return color.equals(that.getColor());
	}

	@Override
	public boolean isTeamOf(Color color) {
		return getColor().equals(color);
	}

	@Override
	public String getName() {
		if (WHITE.equals(color)) {
			return name.toLowerCase();
		}
		return name;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public double getScore() {
		return score;
	}
}
