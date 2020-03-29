package chess.domain.piece;

import java.util.Set;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class Piece {
	private String name;
	private Color color;
	private MovingStrategy movingStrategy;
	private double score;

	public Piece(String name, Color color, PieceType pieceType) {
		this.name = name;
		this.color = color;
		this.movingStrategy = pieceType.getMovingStrategy();
		this.score = pieceType.getScore();
	}

	public Set<Position> findMovablePositions(Position currentPosition, Board board) {
		return movingStrategy.findMovablePositions(currentPosition, board);
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
