package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Division {

    public static final double KNIGHT_SCORE = 2.5;

    public Knight(Color color, Position position) {
        super(color, "n", position);
    }

    @Override
    public void moveToEmpty(Position to, Pieces pieces) {
        int diffRow = Math.abs(position.diffRow(to));
        int diffColumn = Math.abs(position.diffColumn(to));

        if ((diffRow == 2 && diffColumn == 1) || (diffRow == 1 && diffColumn == 2)) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void moveForKill(Position to, Pieces pieces) {
        this.moveToEmpty(to, pieces);
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
    public List<Position> movablePositions(Position position) {
        List<Position> positions = new ArrayList<>();
        positions.add(position.move(2,1));
        positions.add(position.move(2,-1));
        positions.add(position.move(1,2));
        positions.add(position.move(1,-2));
        positions.add(position.move(-1,2));
        positions.add(position.move(-1,-2));
        positions.add(position.move(-2,1));
        positions.add(position.move(-2,-1));

        return positions;
    }

    @Override
    public List<Position> killablePositions(Position position) {
        return movablePositions(position);
    }
}
