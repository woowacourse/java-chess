package chess.domain.piece;

import static chess.domain.piece.Color.*;

import java.util.Map;
import java.util.Set;

import chess.domain.position.Position;

public abstract class Piece {
	private String name;
	private Color color;
	private MovingStrategy movingStrategy;
	private double score;

	public Piece(Color color, String name, MovingStrategy movingStrategy, double score) {
		this.name = nameByColor(color, name);
		this.color = color;
		this.movingStrategy = movingStrategy;
		this.score = score;
	}

	private String nameByColor(Color color, String name) {
		if (color == BLACK) {
			return name.toUpperCase();
		}
		return name;
	}

	public Set<Position> findMovablePositions(Position currentPosition, Map<Position, Piece> pieces) {
		return movingStrategy.findMovablePositions(currentPosition, pieces);
	}

	public boolean isEnemy(Piece that) {
		return !this.color.equals(that.getColor());
	}

	public boolean isSameColor(Color color) {
		return getColor().equals(color);
	}

	public boolean isNotSameColor(Color color) {
		return !getColor().equals(color);
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public double getScore() {
		return score;
	}
}
