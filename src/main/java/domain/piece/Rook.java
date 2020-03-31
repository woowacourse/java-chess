package domain.piece;

import java.util.List;
import java.util.Optional;

import domain.board.Rank;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Rook extends Piece {
	private final double score;

	public Rook(Position position, Team team) {
		super(position, team);
		this.symbol = "r";
		this.score = 5d;
	}

	@Override
	protected boolean validDirection(Direction direction) {
		if (Direction.linearDirection().contains(direction)) {
			return true;
		}
		throw new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION);
	}

	@Override
	protected boolean validStepSize(int rowGap, int columnGap) {
		return true;
	}

	@Override
	protected boolean validateRoute(Direction direction, Position targetPosition, List<Rank> ranks) {
		if (direction.hasPieceInRoute(this.position, targetPosition, ranks)) {
			throw new InvalidPositionException(InvalidPositionException.HAS_PIECE_IN_ROUTE);
		}
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
		int rankIndex = targetPosition.getRow() - 1;
		return ranks.get(rankIndex).getPieces().stream()
			.filter(piece -> piece.getPosition().isSamePosition(targetPosition))
			.findFirst();
	}

	public double getScore() {
		return score;
	}
}
