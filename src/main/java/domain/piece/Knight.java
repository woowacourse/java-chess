package domain.piece;

import java.util.List;
import java.util.Optional;

import domain.board.Rank;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Knight extends Piece {
	private static final String SYMBOL = "n";
	private static final double score = 2.5;

	public Knight(Position position, Team team) {
		super(position, team);
	}

	private Optional<Piece> hasPieceInBoard(List<Rank> ranks, Position targetPosition) {
		return ranks.stream()
			.flatMap(rank -> rank.getPieces().stream())
			.filter(piece -> piece.getPosition().getColumn().getNumber() == targetPosition.getColumn().getNumber()
				&& piece.getPosition().getRow() == targetPosition.getRow())
			.findFirst();
	}

	@Override
	protected boolean validDirection(Direction direction) {
		if (Direction.knightDirection().contains(direction)) {
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
		this.changePosition(targetPosition, ranks);
	}

	@Override
	protected String getSymbol() {
		return SYMBOL;
	}

	@Override
	public double getScore(){
		return score;
	}
}
