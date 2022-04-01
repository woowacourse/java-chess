package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public abstract class Pawn extends Piece {

    private static final String name = "P";
    private static final float score = 1.0f;

    public Pawn(Team team, List<Direction> directions) {
        super(name, directions, team);
    }

    @Override
    public void movable(Position from, Position to) {
        Direction direction = from.findDirection(to, this);
        checkValidFirstMove(from, direction);
        super.movable(from, to);
    }

    abstract void checkValidFirstMove(Position from, Direction direction);

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public float getScore() {
        return score;
    }
}