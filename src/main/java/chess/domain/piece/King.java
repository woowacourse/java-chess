package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class King extends NormalPiece {
	private static final int KING_SCORE = 0;
	private static final int MAX_DISTANCE_OF_KING = 1;

	private King(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, type, movementInfos, currentPosition, new Score(KING_SCORE));
	}

	public static King valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos =  new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT, MAX_DISTANCE_OF_KING),
				new MovementInfo(Direction.LEFT_TOP, MAX_DISTANCE_OF_KING),
				new MovementInfo(Direction.TOP, MAX_DISTANCE_OF_KING),
				new MovementInfo(Direction.RIGHT_TOP, MAX_DISTANCE_OF_KING),
				new MovementInfo(Direction.RIGHT, MAX_DISTANCE_OF_KING),
				new MovementInfo(Direction.RIGHT_BOTTOM, MAX_DISTANCE_OF_KING),
				new MovementInfo(Direction.BOTTOM, MAX_DISTANCE_OF_KING),
				new MovementInfo(Direction.LEFT_BOTTOM, MAX_DISTANCE_OF_KING)));

		return new King(player, Type.KING, movementInfos, currentPosition);
	}
}
