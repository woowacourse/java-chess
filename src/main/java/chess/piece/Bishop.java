package chess.piece;

import chess.position.Position;

import java.util.List;

public class Bishop extends Piece {
	private static final String INITIAL_CHARACTER = "B";

    public Bishop(Team team) {
        super(team);
    }

    @Override
    public List<Position> findReachablePositions(Position start, Position end) {
        return null;
    }

    @Override
    protected String getInitialCharacter() {
        return INITIAL_CHARACTER;
    }
}
