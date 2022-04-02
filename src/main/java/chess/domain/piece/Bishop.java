package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.position.Position;
import java.util.List;

public class Bishop extends MultiStepPiece {

    private static final double BISHOP_SCORE = 3;

    public Bishop(Team team, Position position) {
        super(team, position);
    }

    @Override
    public double getScore() {
        return BISHOP_SCORE;
    }

    @Override
    public List<Position> findPath(Position destination) {
        return super.findPath(destination, Direction.diagonalDirection());
    }
}
