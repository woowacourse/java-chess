package domain.piece;

import static domain.piece.position.InvalidPositionException.*;

import java.util.List;
import java.util.Optional;

import domain.board.Rank;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Pawn extends Piece {
	private static final int MiN_STEP_SIZE_OF_DIAGONAL = 1;

	private final double score;
	private State state;

	public Pawn(Position position, Team team) {
		super(position, team);
		state = State.START;
		this.symbol = "p";
		this.score = 1d;
	}

	@Override
	protected boolean validDirection(Direction direction) {
		if (team.getDirectionValidation().apply(direction)) {
			return true;
		}
		throw new InvalidPositionException(INVALID_DIRECTION);
	}

	@Override
	protected boolean validStepSize(int rowGap, int columnGap) {
		int absStepSize = Math.abs(rowGap);
		if (state.getIsValidStepSize().apply(absStepSize)) {
			return true;
		}
		throw new InvalidPositionException(INVALID_STEP_SIZE);
	}

	@Override
	protected boolean validateRoute(Direction direction, Position targetPosition, List<Rank> ranks) {
		if (direction.hasPieceInRoute(this.position, targetPosition, ranks)) {
			throw new InvalidPositionException(HAS_PIECE_IN_ROUTE);
		}
		return true;
	}

	@Override
	public void move(Position targetPosition, List<Rank> ranks) {
		int rowGap = this.position.calculateRowGap(targetPosition);
		Direction direction = this.findDirection(rowGap, this.position.calculateColumnGap(targetPosition));
		Optional<Piece> piece = hasPieceInBoard(ranks, targetPosition);
		if (Direction.diagonalDirection().contains(direction) && rowGap == MiN_STEP_SIZE_OF_DIAGONAL) {
			piece.ifPresent(targetPiece -> {
				if (targetPiece.team.equals(this.team)) {
					throw new InvalidPositionException(HAS_OUR_TEAM_AT_TARGET_POSITION);
				}
				capture(targetPiece, ranks);
			});
		}
		if (Direction.linearDirection().contains(direction) && piece.isPresent()) {
			throw new InvalidPositionException(HAS_PIECE_AT_TARGET_POSITION);
		}
		this.changePosition(this, targetPosition, ranks);
		this.state = State.RUN;
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
