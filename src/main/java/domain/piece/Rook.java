package domain.piece;

import domain.position.Position;

import java.util.List;

public class Rook extends Division {
    public Rook(Color color, Position position) {
        super(color, "r", position);
    }

    @Override
    public void move(Position to, Pieces pieces) {
        if (position.isOrthogonal(to)) {
            validateNoneBetween(to, pieces);
            position = to;
        }
    }

    private void validateNoneBetween(Position to, Pieces pieces) {
        List<Position> positions = position.getBetween(to);
        if (positions.stream()
                     .anyMatch(pieces::hasPieceOf)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void kill(Position to, Pieces pieces) {
        move(to, pieces);
    }
}
