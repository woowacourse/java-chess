package chess.domain.piece;

import chess.domain.moveStrategy.KnightMove;
import chess.domain.moveStrategy.MoveStrategy;
import chess.domain.position.Position;

import java.util.List;

public class Knight extends Division {
    public static final double KNIGHT_SCORE = 2.5;
    private final MoveStrategy moveStrategy = new KnightMove(color);

    public Knight(Color color) {
        super(color, "n");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return KNIGHT_SCORE;
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
