package domain.piece;

import java.util.Objects;
import java.util.Optional;

import domain.board.Board;
import domain.board.InvalidTurnException;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public abstract class Piece implements Movable {
	protected Position position;
	protected Team team;

	public Piece(Position position, Team team) {
		this.position = position;
		this.team = team;
	}

	private void validateTurn(Team nowTurn) {
		if (this.team != nowTurn) {
			throw new InvalidTurnException(InvalidTurnException.INVALID_TURN);
		}
	}

	public boolean isTeam(Team team) {
		return this.team.equals(team);
	}

	private boolean isInPlace(Position sourcePosition, Position targetPosition) {
		return sourcePosition.equals(targetPosition);
	}

	protected void capture(Piece targetPiece, Board board) {
		board.remove(targetPiece);
	}

	protected void changePosition(Position targetPosition, Board board) {
		board.remove(this);
		this.position = targetPosition;
		board.add(this, targetPosition);
	}

	public String showSymbol() {
		if (this.team == Team.WHITE) {
			return getSymbol();
		}
		return getSymbol().toUpperCase();
	}

	public Position getPosition() {
		return position;
	}

	@Override
	public void canMove(Position targetPosition, Team turn, Board board) {
		validateTurn(turn);
		if (isInPlace(this.position, targetPosition)) {
			throw new InvalidPositionException(InvalidPositionException.IS_IN_PLACE);
		}

		Direction direction = Direction.findDirection(this.position, targetPosition);
		validateDirection(direction);
		validateStepSize(this.position, targetPosition);
		validateRoute(direction, targetPosition, board);
	}

	@Override
	public void move(Position targetPosition, Board board) {
		Optional<Piece> piece = board.findPiece(targetPosition);
		piece.ifPresent(targetPiece -> {
			if (targetPiece.team.equals(this.team)) {
				throw new InvalidPositionException(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
			}
			capture(targetPiece, board);
		});
		this.changePosition(targetPosition, board);
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

	protected abstract void validateDirection(Direction direction);

	protected abstract void validateStepSize(Position sourcePosition, Position targetPosition);

	protected abstract void validateRoute(Direction direction, Position targetPosition, Board ranks);

	public abstract double getScore();

	protected abstract String getSymbol();
}
