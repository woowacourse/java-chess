package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.piece.exception.NotMovableException;

public abstract class Piece {
	private Position position;
	private Color color;
	private Symbol symbol;
	private List<MoveEventListener> moveEventListeners;

	public Piece(Position position, Color color, Symbol symbol) {
		this.position = position;
		this.color = color;
		this.symbol = symbol;
		this.moveEventListeners = new ArrayList<>();
	}

	public Position getPosition() {
		return position;
	}

	public double score() {
		return symbol.getScore();
	}

	public String symbol() {
		return symbol.getName();
	}

	public final boolean isBlack() {
		return color.isBlack();
	}

	public void move(Piece piece) {
		RelativePosition relativePosition = RelativePosition.of(position, piece.position);
		Direction direction = findDirection(relativePosition.getX(), relativePosition.getY());
		validateDirections(direction, movableDirections(piece, direction));
		Path path = new Path(position, piece.position, direction);
		notifyMoveEventListeners(new MoveEvent(this, piece, path));
		position = piece.position;
	}

	public void onMoveEvent(MoveEventListener moveEventListener) {
		moveEventListeners.add(moveEventListener);
	}

	public void notifyMoveEventListeners(MoveEvent moveEvent) {
		for (MoveEventListener listener : moveEventListeners) {
			listener.call(moveEvent);
		}
	}

	private void validateDirections(Direction direction, List<Direction> directions) {
		if (!directions.contains(direction)) {
			throw new NotMovableException();
		}
	}

	public final boolean isSameColor(Piece piece) {
		return color == piece.color;
	}

	public boolean isSameColor(Color color) {
		return this.color == color;
	}

	public boolean isDifferentColor(Color color) {
		return this.color != color;
	}

	public boolean isBlank() {
		return false;
	}

	public boolean isPawn() {
		return false;
	}

	public boolean isKing() {
		return false;
	}

	protected abstract List<Direction> movableDirections(Piece piece, Direction direction);

	protected abstract Direction findDirection(int x, int y);
}
