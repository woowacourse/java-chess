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
	private boolean isNotMoved;

	public Pawn(Position position, Team team) {
		super(position, team);
		directions = Direction.getPawnDirections(team);
		isNotMoved = true;
	}

	@Override
	public String getName() {
		return NameUtils.parseName(NAME, team);
	}

	@Override
	public boolean isNeedCheckPath() {
		System.out.println(isNotMoved);
		return isNotMoved;
	}

	@Override
	public Positions makePath(ChessPiece targetPiece) {
		isNotMoved = false;
		List<Direction> firstMoveDirections = new ArrayList<>(this.directions);
		if (team == Team.WHITE) {
			firstMoveDirections.add(DOUBLE_UP);
			return moveManager.makePath(targetPiece.position, firstMoveDirections);
		}
		firstMoveDirections.add(DOUBLE_DOWN);
		return moveManager.makePath(targetPiece.position, firstMoveDirections);
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
		return (direction == UP || direction == DOWN) && chessPiece.isMatchTeam(Team.BLANK);
	}

	private boolean canToCatch(Direction direction, ChessPiece targetPiece) {
		return isDiagonal(direction) && targetPiece.isNotMatchTeam(Team.BLANK);
	}

	private boolean isDiagonal(Direction direction) {
		return direction == RIGHT_UP || direction == LEFT_UP
			|| direction == RIGHT_DOWN || direction == LEFT_DOWN;
	}
}
