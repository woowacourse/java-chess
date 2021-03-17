package chess.domain.piece;

import chess.domain.board.Position;

import java.util.List;

public class Queen extends Piece {
    private static final String QUEEN_NAME = "Q";

    public Queen(Team team) {
        super(QUEEN_NAME, team);
    }

    @Override
    public List<Position> movablePositions(Position target) {
        return null;
    }
}
