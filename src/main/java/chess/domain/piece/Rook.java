package chess.domain.piece;

import chess.domain.moveStrategy.MoveStrategy;
import chess.domain.moveStrategy.RookMove;
import chess.domain.position.Position;

import java.util.List;

public class Rook extends Division {

    public static final int ROOK_SCORE = 5;

    private final MoveStrategy moveStrategy = new RookMove();

    public Rook(Color color) {
        super(color, "r");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return ROOK_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public List<List<Position>> movablePositions(Position position) {
        return moveStrategy.movablePositions(position);
    }

    @Override
    public List<List<Position>> killablePositions(Position position) {
        return movablePositions(position);
    }
}
