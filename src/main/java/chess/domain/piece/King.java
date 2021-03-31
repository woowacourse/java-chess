package chess.domain.piece;

import chess.domain.moveStrategy.KingMove;
import chess.domain.moveStrategy.MoveStrategy;
import chess.domain.location.Position;

import java.util.List;

public class King extends Division {
    public static final int KING_SCORE = 0;
    private final MoveStrategy moveStrategy = new KingMove(color);

    public King(Color color) {
        super(color, "k");
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double score() {
        return KING_SCORE;
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
