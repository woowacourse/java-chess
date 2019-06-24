package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class Rook extends NormalPiece {
	private Rook(Player player, List<MovementInfo> movementInfos, Position currentPosition) {
		super(player, movementInfos, currentPosition, new Score(5));
	}

	public static Rook valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT, 7),
				new MovementInfo(Direction.TOP, 7),
				new MovementInfo(Direction.RIGHT, 7),
				new MovementInfo(Direction.BOTTOM, 7)));

		return new Rook(player, movementInfos, currentPosition);
	}
}
