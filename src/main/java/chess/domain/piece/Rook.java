package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Division {

    public static final int ROOK_SCORE = 5;

    public Rook(Color color, Position position) {
        super(color, "r", position);
    }

    @Override
    public void moveToEmpty(Position to, Pieces pieces) {
        if (position.isOrthogonal(to)) {
            validateNoneBetween(to, pieces);
            position = to;
        }
    }

    private void validateNoneBetween(Position to, Pieces pieces) {
        List<Position> positions = position.getBetween(to);
        if (positions.stream()
                     .filter(pieces::hasPieceOf)
                     .anyMatch(pieces::hasPieceOf)) {
            throw new IllegalArgumentException();
        }
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
        return ROOK_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    public List<Position> movablePosition(Position position) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(position.positionsOfDirection(1,0));
        positions.addAll(position.positionsOfDirection(-1,0));
        positions.addAll(position.positionsOfDirection(0,1));
        positions.addAll(position.positionsOfDirection(0,-1));

        return positions;
    }
}
