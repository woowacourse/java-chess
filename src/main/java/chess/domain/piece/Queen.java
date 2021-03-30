package chess.domain.piece;

import chess.domain.moveStrategy.MoveStrategy;
import chess.domain.moveStrategy.QueenMove;
import chess.domain.position.Position;

import java.util.List;

public class Queen extends Division {

    public static final int QUEEN_SCORE = 9;
    private final MoveStrategy moveStrategy = new QueenMove(color);

    public Queen(Color color) {
        super(color, "q");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return QUEEN_SCORE;
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
