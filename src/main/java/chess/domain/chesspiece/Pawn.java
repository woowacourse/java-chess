package chess.domain.chesspiece;

import static chess.domain.Direction.*;

import java.util.ArrayList;
import java.util.List;

import chess.domain.Direction;
import chess.domain.MoveManager;
import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.utils.NameUtils;

public class Pawn extends ChessPiece {
	private static final String NAME = "p";

	private final List<Direction> directions;
	private boolean isMoved;

	public Pawn(Position position, Team team) {
		super(position, team);
		directions = Direction.getPawnDirections(team);
		isMoved = false;
	}

	@Override
	public String getName() {
		return NameUtils.parseName(NAME, team);
	}

	@Override
	public boolean isNotNeedCheckPath() {
		return isMoved;
	}

	@Override
	public Positions makePathAndValidate(ChessPiece targetPiece) {
		isMoved = true;
		validateCanGo(targetPiece);
		return moveManager.makePathAndValidate(
			targetPiece.position, getFirstMoveDirection(team));
	}

	private List<Direction> getFirstMoveDirection(Team team) {
		List<Direction> firstMoveDirections = new ArrayList<>(this.directions);
		if (team == Team.WHITE) {
			firstMoveDirections.add(DOUBLE_UP);
		}
		if (team == Team.BLACK) {
			firstMoveDirections.add(DOUBLE_DOWN);
		}
		return firstMoveDirections;
	}

	@Override
	public void validateCanGo(ChessPiece targetPiece) {
		Direction direction = moveManager.calculateDirection(targetPiece.position, directions);
		if (cannotMove(direction, targetPiece)) {
			throw new IllegalArgumentException(MoveManager.CANNOT_MOVE_POSITION);
		}
	}

	private boolean cannotMove(Direction direction, ChessPiece targetPiece) {
		return canMoveForward(direction, targetPiece) == false
			&& canToCatch(direction, targetPiece) == false;
	}

	private boolean canMoveForward(Direction direction, ChessPiece chessPiece) {
		return isCanMoveDirection(direction) && chessPiece.isBlankPiece();
	}

	private boolean isCanMoveDirection(Direction direction) {
		return direction == UP || direction == DOWN
			|| direction == DOUBLE_UP || direction == DOUBLE_DOWN;
	}

	private boolean canToCatch(Direction direction, ChessPiece targetPiece) {
		return isCanCatchDirection(direction) && targetPiece.isNotBlankPiece();
	}

	private boolean isCanCatchDirection(Direction direction) {
		return direction == RIGHT_UP || direction == LEFT_UP
			|| direction == RIGHT_DOWN || direction == LEFT_DOWN;
	}
}
