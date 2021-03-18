package domain.piece;

import domain.position.Position;

import java.util.List;

public class Bishop extends Division{
    public Bishop(Color color, Position position) {
        super(color, "b", position);
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
        if (position.isDiagonal(to)) {
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
        move(to, pieces);
    }
}
