package chess.piece;

import java.util.Arrays;
import java.util.List;

import chess.*;
public class Pawn extends Piece {
	private final List<MovementInfo> attackMovementInfos;

	protected Position position;

	private Pawn(Player player, List<MovementInfo> movementInfos, List<MovementInfo> attackMovementInfos, Position position) {
		super(player, movementInfos, position);
		this.attackMovementInfos = attackMovementInfos;
	}

	public static Pawn getWhite(Position position) {
		List<MovementInfo> movementInfos = Arrays.asList(
				new MovementInfo(Direction.TOP, 2));
		List<MovementInfo> attackMovementInfos = Arrays.asList(
				new MovementInfo(Direction.LEFT_TOP, 1),
				new MovementInfo(Direction.RIGHT_TOP, 1));

		return new Pawn(Player.WHITE, movementInfos, attackMovementInfos, position);
	}

	public static Pawn getBlack(Position position) {
		List<MovementInfo> movementInfos = Arrays.asList(
				new MovementInfo(Direction.BOTTOM, 2));
		List<MovementInfo> attackMovementInfos = Arrays.asList(
				new MovementInfo(Direction.LEFT_BOTTOM, 1),
				new MovementInfo(Direction.RIGHT_BOTTOM, 1));

		return new Pawn(Player.BLACK, movementInfos, attackMovementInfos, position);
	}

	@Override
	public Path getAttackablePath(Position end) {
		return getValidPath(end, attackMovementInfos);
	}
}
