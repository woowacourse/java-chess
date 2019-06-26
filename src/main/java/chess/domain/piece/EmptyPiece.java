package chess.domain.piece;

import chess.domain.*;

public class EmptyPiece extends Piece {
	public EmptyPiece(Player player, Type type, Position position) {
		super(player, type, position);
	}

	public static EmptyPiece valueOf(Player player, Position currentPosition) {
		return new EmptyPiece(Player.EMPTY, Type.EMPTY, currentPosition);
	}

	@Override
	public Path getMovablePath(Position end) {
		return null;
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
