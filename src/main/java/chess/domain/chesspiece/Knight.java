package chess.domain.chesspiece;

import java.util.List;

import chess.domain.Position;
import chess.domain.Team;

public class Knight extends ChessPiece {

	public Knight(Position position, Team team) {
		super("n", position, team);
	}

	@Override
	public boolean canMove(Position position) {
		int y = Math.abs(position.getY() - this.position.getY());
		int x = Math.abs(position.getX() - this.position.getX());
		return Math.abs(x - y) == 1;
	}

	@Override
	public List<Position> makeCanMovePositions() {
		throw new UnsupportedOperationException();
	}
}
