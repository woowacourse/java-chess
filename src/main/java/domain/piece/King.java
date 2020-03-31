package domain.piece;

import java.util.List;
import java.util.Optional;

import domain.board.Rank;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class King extends Piece {
	private static final int MAX_STEP_SIZE = 2;

	private final double score;

	public King(Position position, Team team) {
		super(position, team);
		this.symbol = "k";
		this.score = 0d;
	}

	@Override
	protected boolean validDirection(Direction direction) {
		if (Direction.everyDirection().contains(direction)) {
			return true;
		}
		throw new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION);
	}

	@Override
	protected boolean validStepSize(int rowGap, int columnGap) {
		if ((Math.abs(rowGap) < MAX_STEP_SIZE) && (Math.abs(columnGap) < MAX_STEP_SIZE)) {
			return true;
		}
		throw new InvalidPositionException(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@Override
	protected boolean validateRoute(Direction direction, Position targetPosition, List<Rank> ranks) {
		return true;
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
		this.changePosition(this, targetPosition, ranks);
	}

	private Optional<Piece> hasPieceInBoard(List<Rank> ranks, Position targetPosition) {
		return ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece.getPosition().getColumn().getNumber() == targetPosition.getColumn().getNumber()
				&& piece.getPosition().getRow() == targetPosition.getRow())
			.findFirst();
	}

	public double getScore() {
		return score;
	}
}
