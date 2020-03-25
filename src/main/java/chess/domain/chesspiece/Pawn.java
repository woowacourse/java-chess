package chess.domain.chesspiece;

import java.util.List;

import chess.domain.Position;
import chess.domain.Team;

public class Pawn extends ChessPiece {
    boolean isFirstMove = true;

    public Pawn(Position position, Team team) {
        super("p", position, team);
    }

	@Override
	public boolean canMove(Position position) {
		return false;
	}

	@Override
	public List<Position> makeCanMovePositions() {
		return null;
	}
}
