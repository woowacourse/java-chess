package chess.domain.piece;

import java.util.List;

import chess.domain.*;

public class NormalPiece extends Piece {
	NormalPiece(Player player, List<MovementInfo> movementInfos, Position position, Score score) {
		super(player, movementInfos, position, score);
	}

	@Override
	public void changePosition(Position position) {
		this.position = position;
	}

	@Override
	public boolean isPawn() {
		return false;
	}

	@Override
	public Path getAttackablePath(Position end) {
		return getMovablePath(end);
	}
}
