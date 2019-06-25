package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class Bishop extends NormalPiece {
	private Bishop(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, type, movementInfos, currentPosition, new Score(3));
	}

	public static Bishop valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos =  new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT_TOP, 7),
				new MovementInfo(Direction.RIGHT_TOP, 7),
				new MovementInfo(Direction.RIGHT_BOTTOM, 7),
				new MovementInfo(Direction.LEFT_BOTTOM, 7)));

		return new Bishop(player, Type.BISHOP, movementInfos, currentPosition);
	}
}
