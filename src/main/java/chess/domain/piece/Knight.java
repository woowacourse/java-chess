package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knight extends Division {

    public static final double KNIGHT_SCORE = 2.5;

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
        List<Position> positions = new ArrayList<>();
        positions.add(position.move(2, 1));
        positions.add(position.move(2, -1));
        positions.add(position.move(1, 2));
        positions.add(position.move(1, -2));
        positions.add(position.move(-1, 2));
        positions.add(position.move(-1, -2));
        positions.add(position.move(-2, 1));
        positions.add(position.move(-2, -1));

        return Collections.singletonList(positions);
    }

    @Override
    public List<List<Position>> killablePositions(Position position) {
        return movablePositions(position);
    }
}
