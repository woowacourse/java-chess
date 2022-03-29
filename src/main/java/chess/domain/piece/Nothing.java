package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.postion.Position;

import java.util.List;

public final class Nothing extends Piece {

    public Nothing() {
        super(Team.NOTHING, new MoveStrategy() {
            @Override
            public void isMovable(Position source, Position target) {

            }
        });
    }

    @Override
    public String symbol() {
        return null;
    }

    @Override
    public List<Direction> possibleDirections() {
        return null;
    }

    @Override
    public double score() {
        return 0;
    }
}
