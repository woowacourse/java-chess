package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class King extends Division {

    public static final int KING_SCORE = 0;

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
        List<Position> positions = new ArrayList<>();
        positions.add(position.move(1, 1));
        positions.add(position.move(1, 0));
        positions.add(position.move(1, -1));
        positions.add(position.move(0, 1));
        positions.add(position.move(0, -1));
        positions.add(position.move(-1, 1));
        positions.add(position.move(-1, 0));
        positions.add(position.move(-1, -1));

        return Collections.singletonList(positions);
    }

    @Override
    public List<List<Position>> killablePositions(Position position) {
        return movablePositions(position);
    }
}
