package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.*;

public class EmptyPiece extends Piece {
	public EmptyPiece(Player player, Type type, List<MovementInfo> movementInfos, Position position) {
		super(player, type, movementInfos, position, new Score(0));
	}

	public static EmptyPiece valueOf(Player player, Position currentPosition) {
		List<MovementInfo> movementInfos = new ArrayList<>();
		return new EmptyPiece(Player.EMPTY, Type.EMPTY, movementInfos, currentPosition);
	}

	@Override
	public Path getAttackablePath(Position end) {
		return null;
	}

	@Override
	public void changePosition(Position position) {
		return;
	}

	@Override
	public boolean isPawn() {
		return false;
	}
}
