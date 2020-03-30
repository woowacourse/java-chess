package domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

	protected Direction findDirection(int rowGap, int columnGap) {
		return Arrays.stream(Direction.values())
			.filter(d -> d.getFind().apply(rowGap, columnGap))
			.findFirst()
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION));
	}

	protected Optional<Piece> hasPieceInBoard(List<Rank> ranks, Position targetPosition) {
		return ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece.getPosition().equals(targetPosition))
			.findFirst();
	}

	protected void capture(Piece targetPiece, List<Rank> ranks) {
		int targetRankIndex = targetPiece.position.getRow() - 1;
		ranks.get(targetRankIndex).getPieces().remove(targetPiece);
	}

	protected void changePosition(Position targetPosition, List<Rank> ranks) {
		int sourceRankIndex = this.position.getRow() - 1;
		ranks.get(sourceRankIndex).getPieces().remove(this);

		this.position = targetPosition;
		int targetRankIndex = targetPosition.getRow() - 1;
		ranks.get(targetRankIndex).getPieces().add(this);
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
	public boolean canMove(Position targetPosition, Team turn, List<Rank> ranks) {
		validateTurn(turn);
		if (isInPlace(this.position, targetPosition)) {
			throw new InvalidPositionException(InvalidPositionException.IS_IN_PLACE);
		}
		int rowGap = this.position.calculateRowGap(targetPosition);
		int columnGap = this.position.calculateColumnGap(targetPosition);
		Direction direction = findDirection(rowGap, columnGap);

		return validateDirection(direction) && validateStepSize(rowGap, columnGap) &&
			validateRoute(direction, targetPosition, ranks);
	}

	@Override
	public void move(Position targetPosition, List<Rank> ranks) {
		Optional<Piece> piece = hasPieceInBoard(ranks, targetPosition);
		piece.ifPresent(targetPiece -> {
			if (targetPiece.team.equals(this.team)) {
				throw new InvalidPositionException(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
			}
			capture(targetPiece, ranks);
		});
		this.changePosition(targetPosition, ranks);
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

	protected abstract boolean validateDirection(Direction direction);

	protected abstract boolean validateStepSize(int rowGap, int columnGap);

	protected abstract boolean validateRoute(Direction direction, Position targetPosition, List<Rank> ranks);

	public abstract double getScore();

	protected abstract String getSymbol();
}
