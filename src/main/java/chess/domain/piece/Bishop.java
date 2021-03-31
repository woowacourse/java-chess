package chess.domain.piece;

import chess.domain.moveStrategy.BishopMove;
import chess.domain.moveStrategy.MoveStrategy;
import chess.domain.location.Position;

import java.util.List;

public class Bishop extends Division {
    public static final int BISHOP_SCORE = 3;
    private final MoveStrategy moveStrategy = new BishopMove(color);

    public Bishop(Color color) {
        super(color, "b");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return BISHOP_SCORE;
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
