package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Side;
import chess.domain.position.Column;
import chess.domain.position.Position;

public abstract class Piece implements Movable {
	protected double score;
	protected String name;
	protected Side side;
	protected Position position;

	public Piece(Side side, Position position) {
		this.side = side;
		this.position = position;
	}

	@Override
	public void move(Position targetPosition) {
		this.position = targetPosition;
	}

	@Override
	public boolean canMove(Position targetPosition) {
		if (position.equals(targetPosition)) {
			return false;
		}
		return isInPath(targetPosition);
	}

	public boolean canNotMove(Position targetPosition) {
		return !this.canMove(targetPosition);
	}

	public boolean isBlock(Position source, Position target) {
		return isBetween(source, target) && findDirection(target).isSameDirection(position, source);
	}

	public boolean isBetween(Position source, Position target) {
		if (source.isSameCol(target)) {
			return isBetweenRow(source, target);
		}
		if (source.isSameRow(target)) {
			return isBetweenCol(source, target);
		}
		return isBetweenRow(source, target) && isBetweenCol(source, target);
	}

	public boolean isBetweenRow(Position source, Position target) {
		if (source.compareToRow(target) > 0) {
			return isBetweenRow(target, source);
		}
		return source.compareToRow(position) < 0 && position.compareToRow(target) < 0;
	}

	public boolean isBetweenCol(Position source, Position target) {
		if (source.compareToCol(target) > 0) {
			return isBetweenCol(target, source);
		}
		return source.compareToCol(position) < 0 && position.compareToCol(target) < 0;
	}

	public Direction findDirection(Position target) {
		return position.findDirection(target);
	}

	public boolean isAttackForward(Position target) {
		return side.isAttackForward(position, target);
	}

	public boolean isSamePosition(Position position) {
		return this.position.equals(position);
	}

	public boolean isSameSide(Piece piece) {
		return this.side.equals(piece.side);
	}

	public boolean isSameSide(Side side) {
		return this.side.equals(side);
	}

	public boolean isPawn() {
		return false;
	}

	public boolean isSameCol(Column column){
		return position.isSameCol(column);
	}

	public String getName() {
		if (side == Side.BLACK) {
			return name.toUpperCase();
		}
		return name;
	}

	public Position getPosition() {
		return position;
	}

	public double getScore() {
		return score;
	}

	public abstract boolean isInPath(Position targetPosition);
}
