package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Piece {
    public Bishop(Position position, Team team) {
        super(position, Name.BISHOP, team);
    }

    @Override
    public boolean canNotMoveTo(Piece that) {
        return !createMovableArea().contains(that.position);
    }

    @Override
    protected List<Position> createMovableArea() {
        return Position.getPositions()
                .stream()
                .filter(position -> !position.equals(this.position))
                .filter(this::isDiagonal)
                .collect(Collectors.toList());
    }

    private boolean isDiagonal(Position position) {
        return Math.abs(position.getColumnGap(this.position)) == Math.abs(position.getRowGap(this.position));
    }
}
