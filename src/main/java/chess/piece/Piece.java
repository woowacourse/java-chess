package chess.piece;

import java.util.List;

import chess.*;
import chess.exception.NotFoundPathException;

public abstract class Piece {
	private final Player player;
	private final List<MovementInfo> movementInfos;

	protected Position position;

	public Piece(Player player, List<MovementInfo> movementInfos, Position position) {
		this.player = player;
		this.movementInfos = movementInfos;
		this.position = position;
	}

	public Path getMovablePath(Position end) {
		return getValidPath(end, movementInfos);
	}

	public abstract Path getAttackablePath(Position end);

	private int getValidDistance(MovementInfo movementInfo, Direction direction) {
		int distance = movementInfo.getMaxDistance();
		if (distance > position.getMaxDistance(direction)) {
			distance = position.getMaxDistance(direction);
		}
		return distance;
	}

	Path getValidPath(Position end, List<MovementInfo> movementInfos) {
		for (MovementInfo movementInfo : movementInfos) {
			Path path = getPath(movementInfo);
			if (path.contains(end)) {
				path.removeEndPosition();
				return path;
			}
		}
		throw new NotFoundPathException();
	}

	private Path getPath(MovementInfo movementInfo) {
		Path path = new Path();
		Direction direction = movementInfo.getDirection();
		Position currentPosition = position;
		int validDistance = getValidDistance(movementInfo, direction);

		for (int i = 0; i < validDistance; i++) {
			currentPosition = currentPosition.move(direction);
			path.add(currentPosition);
		}
		return path;
	}

	public boolean isSamePosition(Position position) {
		return this.position.equals(position);
	}

	public boolean isMine(Player player) {
		return this.player.equals(player);
	}


}
