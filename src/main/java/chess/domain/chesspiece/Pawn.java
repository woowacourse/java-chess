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

	private boolean isMoved;

	public Pawn(Position position, Team team) {
		super(position, team);
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
		validateCanGo(targetPiece);
		isMoved = true;
		return moveManager.makePath(
			targetPiece.position, getCanGoDirections());
	}

	@Override
	public void validateCanGo(ChessPiece targetPiece) {
		Direction direction = moveManager.getMatchDirection(targetPiece.position);
		moveManager.validateMove(direction, getCanGoDirections());
		if (cannotMove(direction, targetPiece)) {
			throw new IllegalArgumentException(MoveManager.CANNOT_MOVE_POSITION);
		}
	}

	private List<Direction> getCanGoDirections() {
		List<Direction> directions = new ArrayList<>();
		directions.addAll(Direction.getPawnDirections(team));
		if (isMoved) {
			return directions;
		}
		if (team == Team.WHITE) {
			directions.add(DOUBLE_UP);
			return directions;
		}
		directions.add(DOUBLE_DOWN);
		return directions;
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
