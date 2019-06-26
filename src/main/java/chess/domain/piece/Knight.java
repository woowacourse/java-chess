package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class Knight extends NormalPiece {
	private static final double KNIGHT_SCORE = 2.5;
	private static final int MAX_DISTANCE_OF_KNIGHT = 1;

	private Knight(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, type, movementInfos, currentPosition, new Score(KNIGHT_SCORE));
	}

	public static Knight valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT_LEFT_BOTTOM, MAX_DISTANCE_OF_KNIGHT),
				new MovementInfo(Direction.LEFT_LEFT_TOP, MAX_DISTANCE_OF_KNIGHT),
				new MovementInfo(Direction.TOP_TOP_LEFT, MAX_DISTANCE_OF_KNIGHT),
				new MovementInfo(Direction.TOP_TOP_RIGHT, MAX_DISTANCE_OF_KNIGHT),
				new MovementInfo(Direction.RIGHT_RIGHT_TOP, MAX_DISTANCE_OF_KNIGHT),
				new MovementInfo(Direction.RIGHT_RIGHT_BOTTOM, MAX_DISTANCE_OF_KNIGHT),
				new MovementInfo(Direction.BOTTOM_BOTTOM_RIGHT, MAX_DISTANCE_OF_KNIGHT),
				new MovementInfo(Direction.BOTTOM_BOTTOM_LEFT, MAX_DISTANCE_OF_KNIGHT)));

		return new Knight(player, Type.KNIGHT, movementInfos, currentPosition);
	}
}
