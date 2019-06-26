package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class Pawn extends Piece {
	private static int WHITE_INITIAL_Y = 2;
	private static int BLACK_INITIAL_Y = 7;

	private final List<MovementInfo> movementInfos;
	private final List<MovementInfo> attackMovementInfos;

	private Pawn(Player player, List<MovementInfo> movementInfos, List<MovementInfo> attackMovementInfos, Position position) {
		super(player, Type.PAWN, position);
		this.movementInfos = movementInfos;
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

		return new Pawn(Player.WHITE, movementInfos, attackMovementInfos, position);
	}

	private static Pawn getBlack(Position position) {
		List<MovementInfo> movementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.BOTTOM, 2)));
		List<MovementInfo> attackMovementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT_BOTTOM, 1),
				new MovementInfo(Direction.RIGHT_BOTTOM, 1)));

		return new Pawn(Player.BLACK, movementInfos, attackMovementInfos, position);
	}

	@Override
	public Path getMovablePath(Position end) {
		if (this.isMine(Player.BLACK)) {
			if (position.getCoordinateY() == BLACK_INITIAL_Y) {
				List<MovementInfo> movementInfos = new ArrayList<>();
				movementInfos.add(new MovementInfo(Direction.BOTTOM, 2));
				return getValidPath(end, movementInfos);
			}
			List<MovementInfo> movementInfos = new ArrayList<>();
			movementInfos.add(new MovementInfo(Direction.BOTTOM, 1));
			return getValidPath(end, movementInfos);
		}
		if (position.getCoordinateY() == WHITE_INITIAL_Y) {
			List<MovementInfo> movementInfos = new ArrayList<>();
			movementInfos.add(new MovementInfo(Direction.TOP, 2));
			return getValidPath(end, movementInfos);
		}
		List<MovementInfo> movementInfos = new ArrayList<>();
		movementInfos.add(new MovementInfo(Direction.TOP, 1));
		return getValidPath(end, movementInfos);
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
