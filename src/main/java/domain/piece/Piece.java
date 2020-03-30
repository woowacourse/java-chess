package domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import domain.board.InvalidTurnException;
import domain.board.Rank;
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

	public Position getPosition() {
		return position;
	}

	private void validateTurn(Team nowTurn) {
		if (this.team != nowTurn) {
			throw new InvalidTurnException(InvalidTurnException.INVALID_TURN);
		}
	}

	protected Direction findDirection(int rowGap, int columnGap) {
		return Arrays.stream(Direction.values())
			.filter(d -> d.getFind().apply(rowGap, columnGap))
			.findFirst()
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION));
	}

	protected void capture(Piece targetPiece, List<Rank> ranks) {
		int targetPieceRowIndex = targetPiece.position.getRow() - 1;
		ranks.get(targetPieceRowIndex).getPieces().remove(targetPiece);
	}

	protected void changePosition(Position targetPosition, List<Rank> ranks) {
		this.position = targetPosition;
		int rankIndex = targetPosition.getRow() - 1;
		ranks.get(rankIndex).getPieces().add(this);
	}

	protected abstract boolean validDirection(Direction direction);

	protected abstract boolean validStepSize(int rowGap, int columnGap);

	protected abstract boolean validateRoute(Direction direction, Position targetPosition, List<Rank> ranks);

	public abstract String showSymbol();

	private boolean isInPlace(Position sourcePosition, Position targetPosition) {
		return sourcePosition.equals(targetPosition);
	}

	@Override
	public boolean canMove(Position targetPosition, Team turn, List<Rank> ranks) {
		validateTurn(turn);
		if (isInPlace(this.position, targetPosition)) {
			throw new InvalidPositionException(InvalidPositionException.IS_IN_PLACE);
		}
		int rowGap = this.position.calculateRowGap(targetPosition);
		int columnGap = this.position.calculateColumnGap(targetPosition);
		Direction direction = findDirection(rowGap, columnGap);

		return validDirection(direction) && validStepSize(rowGap, columnGap) &&
			validateRoute(direction, targetPosition, ranks);
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
