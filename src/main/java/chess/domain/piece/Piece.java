package chess.domain.piece;

import static chess.domain.piece.Color.*;

import java.util.Set;
import java.util.function.Function;

import chess.domain.position.Position;

public class Piece {
	private String name;
	private Color color;
	private MovingStrategy movingStrategy;
	private double score;

	public Piece(Color color, PieceType pieceType) {
		this.color = color;
		this.name = nameByColor(color, pieceType.getName());
		this.movingStrategy = pieceType.getMovingStrategy();
		this.score = pieceType.getScore();
	}

	private String nameByColor(Color color, String name) {
		if (color == BLACK) {
			return name.toUpperCase();
		}
		return name;
	}

	public Set<Position> findMovablePositions(Position currentPosition, Function<Position, Piece> pieceFinder) {
		return movingStrategy.findMovablePositions(currentPosition, pieceFinder);
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
