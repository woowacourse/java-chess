package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public class Queen extends Division {
    public Queen(Color color, Position position) {
        super(color, "q", position);
    }

    @Override
    public void move(Position to, Pieces pieces) {
        if (position.isOrthogonal(to)) {
            validateNoneBetween(to, pieces);
            position = to;
            return;
        }
        if (position.isDiagonal(to)) {
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

    @Override
    public boolean isKing() {
        return false;
    }
}
