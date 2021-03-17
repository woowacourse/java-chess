package domain.piece;

import domain.position.Position;
import domain.position.Row;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Division {
    public Pawn(Color color, Position position) {
        super(color, "p", position);
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
        if (possiblePositions().contains(to)) {
            this.position = to;
        }
    }

    @Override
    public void kill(Position to, List<Piece> pieces) {

    }

    public List<Position> possiblePositions() {
        List<Position> positions = new ArrayList<>();
        if (isBlack()) {
            if (this.position.hasRow(Row.SEVEN)) {
                positions.add(this.position.moveBy(0, -2));
            }
            positions.add(this.position.moveBy(0,-1));
        }
        if (isWhite()) {
            if (this.position.hasRow(Row.TWO)) {
                positions.add(this.position.moveBy(0, 2));
            }
            positions.add(this.position.moveBy(0,1));
        }
        return positions;
    }
}
