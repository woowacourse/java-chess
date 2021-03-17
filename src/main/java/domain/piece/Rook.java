package domain.piece;

import domain.position.Position;

import java.util.List;

public class Rook extends Division{
    public Rook(Color color, Position position) {
        super(color, "r", position);
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
        if (position.isOrthogonal(to)) {
            List<Position> positions = position.getBetween(to);
            for (Piece piece : pieces) {
                if (positions.contains(piece.getPosition())) {
                    throw new IllegalArgumentException();
                }
            }
            position = to;
        }
    }

    @Override
    public void kill(Position to, List<Piece> pieces) {
        //validate(to);
    }
}
