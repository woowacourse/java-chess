package domain.piece;

import java.util.List;
import java.util.Optional;

import domain.board.Rank;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Bishop extends Piece {
	private final double score;

	public Bishop(Position position, Team team) {
		super(position, team);
		this.symbol = "b";
		this.score = 3d;
	}

	@Override
	protected boolean validDirection(Direction direction) {
		if (Direction.diagonalDirection().contains(direction)) {
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
			System.out.println("target :: "+targetPiece.position.getColumn().getColumnName() + targetPiece.getPosition().getRow() + " " + targetPiece.team);
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
