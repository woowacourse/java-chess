package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.moveStrategy.ToEndOfLineStrategy;

import java.util.List;

public class Bishop extends Piece {
    private static final String BISHOP_NAME = "B";
    private static final double SCORE = 3;

    public Bishop(Team team) {
        super(BISHOP_NAME, team, SCORE, new ToEndOfLineStrategy(), false);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        return movablePositions(target, Direction.diagonal());
    }
}
