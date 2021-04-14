package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.moveStrategy.ToEndOfLineStrategy;

import java.util.List;

public class Rook extends Piece {
    private static final String ROOK_NAME = "R";
    private static final double SCORE = 5;

    public Rook(Team team) {
        super(ROOK_NAME, team, SCORE, new ToEndOfLineStrategy(), false);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        return movablePositions(target, Direction.straight());
    }
}
