package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class Pawn extends Piece {
	private final List<MovementInfo> attackMovementInfos;

	private Pawn(Player player, List<MovementInfo> movementInfos, List<MovementInfo> attackMovementInfos, Position position, Score score) {
		super(player, Type.PAWN, movementInfos, position, score);
		this.attackMovementInfos = attackMovementInfos;
	}

	public static Pawn valueOf(Player player, Position position) {
		if (player.equals(Player.WHITE)) {
			return getWhite(position);
		}
		return getBlack(position);
	}

	private static Pawn getWhite(Position position) {
		List<MovementInfo> movementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.TOP, 2)));
		List<MovementInfo> attackMovementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT_TOP, 1),
				new MovementInfo(Direction.RIGHT_TOP, 1)));

		return new Pawn(Player.WHITE, movementInfos, attackMovementInfos, position, new Score(1));
	}

	private static Pawn getBlack(Position position) {
		List<MovementInfo> movementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.BOTTOM, 2)));
		List<MovementInfo> attackMovementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT_BOTTOM, 1),
				new MovementInfo(Direction.RIGHT_BOTTOM, 1)));

		return new Pawn(Player.BLACK, movementInfos, attackMovementInfos, position, new Score(1));
	}

	@Override
	public void changePosition(Position position) {
		super.changeMovementInfo(getMovementInfo());
		this.position = position;
	}

	private MovementInfo getMovementInfo() {
		if(this.isMine(Player.BLACK)) {
			return new MovementInfo(Direction.BOTTOM, 1);
		}
		return new MovementInfo(Direction.TOP, 1);
	}

	@Override
	public boolean isPawn() {
		return true;
	}

	@Override
	public Path getAttackablePath(Position end) {
		return getValidPath(end, attackMovementInfos);
	}
}
