package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class Queen extends NormalPiece {
	private static final double QUEEN_SCORE = 9;
	private static final int MAX_DISTANCE_OF_QUEEN = 7;

	private Queen(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, type, movementInfos, currentPosition, new Score(QUEEN_SCORE));
	}

	public static Queen valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT, MAX_DISTANCE_OF_QUEEN),
				new MovementInfo(Direction.LEFT_TOP, MAX_DISTANCE_OF_QUEEN),
				new MovementInfo(Direction.TOP, MAX_DISTANCE_OF_QUEEN),
				new MovementInfo(Direction.RIGHT_TOP, MAX_DISTANCE_OF_QUEEN),
				new MovementInfo(Direction.RIGHT, MAX_DISTANCE_OF_QUEEN),
				new MovementInfo(Direction.RIGHT_BOTTOM, MAX_DISTANCE_OF_QUEEN),
				new MovementInfo(Direction.BOTTOM, MAX_DISTANCE_OF_QUEEN),
				new MovementInfo(Direction.LEFT_BOTTOM, MAX_DISTANCE_OF_QUEEN)));

		return new Queen(player, Type.QUEEN, movementInfos, currentPosition);
	}
}
