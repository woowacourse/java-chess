package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.*;

public class Knight extends NormalPiece {
	private static final double KNIGHT_SCORE = 2.5;
	private static final int MAX_DISTANCE_OF_KNIGHT = 1;

	private Knight(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, type, movementInfos, currentPosition, new Score(KNIGHT_SCORE));
	}

	public static Knight valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos = new ArrayList<>();
		Direction.getKnightDirection().forEach(direction ->
				movementInfos.add(new MovementInfo(direction, MAX_DISTANCE_OF_KNIGHT)));
		return new Knight(player, Type.KNIGHT, movementInfos, currentPosition);
	}
}
