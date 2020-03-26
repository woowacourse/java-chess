package domain.piece;

import java.util.Arrays;
import java.util.Objects;

import domain.board.InvalidTurnException;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public abstract class Piece implements Movable {
	protected Position position;
	protected Team team;
	protected String SYMBOL;

	public Piece(Position position, Team team) {
		this.position = position;
		this.team = team;
	}

	public Position getPosition() {
		return position;
	}

	public String showSymbol() {
		if (this.team == Team.WHITE) {
			return this.SYMBOL;
		}
		return this.SYMBOL.toUpperCase();
	}

	protected void validateTurn(Team nowTurn) {
		if (this.team != nowTurn) {
			throw new InvalidTurnException(InvalidTurnException.INVALID_TURN);
		}
	}

	private Direction findDirection(int rowGap, int columnGap) {
		return Arrays.stream(Direction.values())
			.filter(d -> d.getFind().apply(rowGap, columnGap))
			.findFirst()
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_TARGET_POSITION));
	}

	abstract boolean validDirection(Direction direction);

	@Override
	public boolean canMove(Position targetPosition, Team turn) {
		validateTurn(turn);
		int rowGap = this.position.calculateRowGap(targetPosition);
		int columnGap = this.position.calculateColumnGap(targetPosition);
		Direction direction = findDirection(rowGap, columnGap);
		if (validDirection(direction)) {
			return true;
		}
		throw new InvalidPositionException(InvalidPositionException.INVALID_TARGET_POSITION);
	}

	@Override
	public void move() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return Objects.equals(position, piece.position) &&
			team == piece.team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, team);
	}
}
