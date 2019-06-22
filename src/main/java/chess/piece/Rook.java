package chess.piece;

import java.util.Arrays;
import java.util.List;

import chess.*;

public class Rook extends NormalPiece {
	private Rook(Player player, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, movementInfos, currentPosition);
	}

	public static Rook valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos = Arrays.asList(
				new MovementInfo(Direction.LEFT, 7),
				new MovementInfo(Direction.TOP, 7),
				new MovementInfo(Direction.RIGHT, 7),
				new MovementInfo(Direction.BOTTOM, 7));

		return new Rook(player, movementInfos, currentPosition);
	}

	@Override
	public Path getAttackablePath(Position end) {
		return getMovablePath(end);
	}
}
