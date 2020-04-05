package domain.piece;

import java.util.Objects;
import java.util.Optional;

import domain.board.Board;
import domain.board.InvalidTurnException;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public abstract class Piece{
	protected Position position;
	protected Team team;

	public Piece(Position position, Team team) {
		this.position = position;
		this.team = team;
	}

	public void move(Position targetPosition, Team turn, Board board) {
		validateMovement(targetPosition, turn, board);

		Optional<Piece> targetPiece = board.findPiece(targetPosition);
		targetPiece.ifPresent(target -> {
			boolean isOurTeam = target.team.equals(this.team);
			if (isOurTeam) {
				throw new InvalidPositionException(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
			}
			capture(target, board);
		});

		this.changePosition(targetPosition, board);
	}

	protected void validateMovement(Position targetPosition, Team turn, Board board) {
		validateTurn(turn);
		validateIsInPlace(targetPosition);
		Direction direction = Direction.findDirection(this.position, targetPosition);
		validateDirection(direction);
		validateStepSize(this.position, targetPosition);
		validateRoute(direction, targetPosition, board);
	}

	private void validateTurn(Team nowTurn) {
		boolean isNotThisTurn = !this.team.equals(nowTurn);

		if (isNotThisTurn) {
			throw new InvalidTurnException(InvalidTurnException.INVALID_TURN);
		}
	}

	private void validateIsInPlace(Position targetPosition) {
		boolean isInPlace = this.position.equals(targetPosition);

		if (isInPlace) {
			throw new InvalidPositionException(InvalidPositionException.IS_IN_PLACE);
		}
	}

	protected abstract void validateDirection(Direction direction);

	protected abstract void validateStepSize(Position sourcePosition, Position targetPosition);

	protected abstract void validateRoute(Direction direction, Position targetPosition, Board ranks);

	protected void capture(Piece targetPiece, Board board) {
		board.remove(targetPiece);
	}

	protected void changePosition(Position targetPosition, Board board) {
		board.remove(this);
		this.position = targetPosition;
		board.add(this, targetPosition);
	}

	public boolean isTeam(Team team) {
		return this.team.equals(team);
	}

	public String showSymbol() {
		boolean isWhitePiece = Team.WHITE.equals(this.team);

		if (isWhitePiece) {
			return getSymbol();
		}
		return getSymbol().toUpperCase();
	}

	public Position getPosition() {
		return position;
	}

	public abstract double getScore();

	protected abstract String getSymbol();

	@Override
	public int hashCode() {
		return Objects.hash(position, team);
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
}
