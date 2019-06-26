package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class Bishop extends NormalPiece {
	private static final int BISHOP_SCORE = 3;
	private static final int MAX_DISTANCE_OF_BISHOP = 7;

	private Bishop(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, type, movementInfos, currentPosition, new Score(BISHOP_SCORE));
	}

	public static Bishop valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT_TOP, MAX_DISTANCE_OF_BISHOP),
				new MovementInfo(Direction.RIGHT_TOP, MAX_DISTANCE_OF_BISHOP),
				new MovementInfo(Direction.RIGHT_BOTTOM, MAX_DISTANCE_OF_BISHOP),
				new MovementInfo(Direction.LEFT_BOTTOM, MAX_DISTANCE_OF_BISHOP)));

		return new Bishop(player, Type.BISHOP, movementInfos, currentPosition);
	}
}
