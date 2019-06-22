package chess.piece;

import java.util.List;

import chess.MovementInfo;
import chess.Path;
import chess.Player;
import chess.Position;

public class NormalPiece extends Piece {
	NormalPiece(Player player, List<MovementInfo> movementInfos, Position position) {
		super(player, movementInfos, position);
	}

	@Override
	public Path getAttackablePath(Position end) {
		return getMovablePath(end);
	}
}
