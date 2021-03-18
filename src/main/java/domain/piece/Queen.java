package domain.piece;

import domain.position.Position;

import java.util.List;

public class Queen extends Division{
    public Queen(Color color, Position position) {
        super(color, "q", position);
    }

    @Override
    public void move(Position to, Pieces pieces) {
        if (position.isOrthogonal(to)) {
            List<Position> positions = position.getBetween(to);
            for (Piece piece : pieces.toList()) {
                if (positions.contains(piece.getPosition())) {
                    throw new IllegalArgumentException();
                }
            }
            position = to;
            return;
        }

        if (position.isDiagonal(to)) {
            List<Position> positions = position.getBetween(to);
            for (Piece piece : pieces.toList()) {
                if (positions.contains(piece.getPosition())) {
                    throw new IllegalArgumentException();
                }
            }
            position = to;
        }


    }

    @Override
    public void kill(Position to, Pieces pieces) {
        move(to, pieces);
    }
}
