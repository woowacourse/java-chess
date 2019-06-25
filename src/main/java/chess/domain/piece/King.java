package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class King extends NormalPiece {
	private King(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, type, movementInfos, currentPosition, new Score(0));
	}

	public static King valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos =  new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT, 1),
				new MovementInfo(Direction.LEFT_TOP, 1),
				new MovementInfo(Direction.TOP, 1),
				new MovementInfo(Direction.RIGHT_TOP, 1),
				new MovementInfo(Direction.RIGHT, 1),
				new MovementInfo(Direction.RIGHT_BOTTOM, 1),
				new MovementInfo(Direction.BOTTOM, 1),
				new MovementInfo(Direction.LEFT_BOTTOM, 1)));

		return new King(player, Type.KING, movementInfos, currentPosition);
	}
}
