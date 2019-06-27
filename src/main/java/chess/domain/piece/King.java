package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.*;

public class King extends NormalPiece {
	private static final int KING_SCORE = 0;
	private static final int MAX_DISTANCE_OF_KING = 1;

	private King(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, type, movementInfos, currentPosition, new Score(KING_SCORE));
	}

	public static King valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos = new ArrayList<>();
		Direction.getKingDirection().forEach(direction ->
				movementInfos.add(new MovementInfo(direction, MAX_DISTANCE_OF_KING)));
		return new King(player, Type.KING, movementInfos, currentPosition);
	}
}
