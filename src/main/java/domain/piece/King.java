package domain.piece;

import domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Division {
    public King(Color color, Position position) {
        super(color, "k", position);
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
        List<Position> possiblePositions = getKingPositions();
        if (possiblePositions.contains(to)) {
            position = to;
        }
    }

    private List<Position> getKingPositions() {
        List<Position> possiblePositions = new ArrayList<>();
        add(possiblePositions, 0, 1);
        add(possiblePositions, 0, -1);
        add(possiblePositions, 1, 0);
        add(possiblePositions, -1, 0);
        add(possiblePositions, 1, 1);
        add(possiblePositions, -1, 1);
        add(possiblePositions, 1, -1);
        add(possiblePositions, -1, -1);
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
