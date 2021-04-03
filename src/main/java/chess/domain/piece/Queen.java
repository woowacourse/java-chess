package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.moveStrategy.ToEndOfLineStrategy;

import java.util.List;

public class Queen extends Piece {
    private static final String QUEEN_NAME = "Q";
    private static final double SCORE = 9;

    public Queen(Team team) {
        super(QUEEN_NAME, team, SCORE, new ToEndOfLineStrategy(), false);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        return movablePositions(target, Direction.all());
    }
}
