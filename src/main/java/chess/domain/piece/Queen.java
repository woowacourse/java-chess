package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class Queen extends NormalPiece {
	private Queen(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, type, movementInfos, currentPosition, new Score(9));
	}

	public static Queen valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos =  new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT, 7),
				new MovementInfo(Direction.LEFT_TOP, 7),
				new MovementInfo(Direction.TOP, 7),
				new MovementInfo(Direction.RIGHT_TOP, 7),
				new MovementInfo(Direction.RIGHT, 7),
				new MovementInfo(Direction.RIGHT_BOTTOM, 7),
				new MovementInfo(Direction.BOTTOM, 7),
				new MovementInfo(Direction.LEFT_BOTTOM, 7)));

		return new Queen(player, Type.QUEEN, movementInfos, currentPosition);
	}
}
