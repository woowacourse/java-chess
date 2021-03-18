package domain.piece;

import domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Division {
    public Knight(Color color, Position position) {
        super(color, "n", position);
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
        List<Position> possiblePositions = getKnightPositions();
        if (possiblePositions.contains(to)) {
            position = to;
        }
    }

    private List<Position> getKnightPositions() {
        List<Position> possiblePositions = new ArrayList<>();
        add(possiblePositions, 1, 2);
        add(possiblePositions, 1, -2);
        add(possiblePositions, -1, 2);
        add(possiblePositions, -1, -2);
        add(possiblePositions, 2, 1);
        add(possiblePositions, 2, -1);
        add(possiblePositions, -2, 1);
        add(possiblePositions, -2, -1);
        return possiblePositions;
    }

    private void add(List<Position> possiblePositions, int columnValue, int rowValue) {
        try {
            possiblePositions.add(position.moveBy(columnValue, rowValue));
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Override
    public void kill(Position to, List<Piece> pieces) {
        move(to, pieces);
    }
}
