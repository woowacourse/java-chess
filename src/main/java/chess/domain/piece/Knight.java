package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class Knight extends NormalPiece {
	private Knight(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, type, movementInfos, currentPosition);
	}

	public static Knight valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT_LEFT_BOTTOM, 1),
				new MovementInfo(Direction.LEFT_LEFT_TOP, 1),
				new MovementInfo(Direction.TOP_TOP_LEFT, 1),
				new MovementInfo(Direction.TOP_TOP_RIGHT, 1),
				new MovementInfo(Direction.RIGHT_RIGHT_TOP, 1),
				new MovementInfo(Direction.RIGHT_RIGHT_BOTTOM, 1),
				new MovementInfo(Direction.BOTTOM_BOTTOM_RIGHT, 1),
				new MovementInfo(Direction.BOTTOM_BOTTOM_LEFT, 1)));

		return new Knight(player, Type.KNIGHT, movementInfos, currentPosition);
	}
}
