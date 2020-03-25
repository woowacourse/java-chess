package chess.domain.chesspiece;

import static chess.domain.chesspiece.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.Move;
import chess.domain.Position;
import chess.domain.Team;

public class Bishop extends ChessPiece {
	public Bishop(Position position, Team team) {
		super("b", position, team);
	}

	@Override
	public boolean canMove(Position position) {
		int x = this.position.getX() - position.getX();
		int y = this.position.getY() - position.getY();
		return x == y;
	}

	@Override
	public List<Position> makeCanMovePositions() {
		return Move.makePassablePath(Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN), this.position);
	}

}
