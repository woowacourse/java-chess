package chess.piece;

import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn {

    private final Position position;

    public Pawn(final Position position) {
        this.position = position;
    }

    public Pawn move(final Position newPosition) {
        if (!calculateCanMovePositions().contains(newPosition)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        return new Pawn(newPosition);
    }

    private List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        if (position.canMoveUp()) {
            positions.add(position.moveUp());
        }
        return positions;
    }
}
