package chess.domain.piece;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.position.Position;

public class Queen extends Piece {
    public Queen(Position position, Team team) {
        super(position, Name.QUEEN, team);
    }

    @Override
    public boolean canNotMoveTo(Piece that) {
        return isSameTeam(that.team) || !createMovableArea().contains(that.position);
    }

    @Override
    protected List<Position> createMovableArea() {
        return Position.getPositions()
                .stream()
                .filter(position -> !position.equals(this.position))
                .filter(this::isQueen)
                .collect(Collectors.toList());
    }

    private boolean isQueen(Position position) {
        return isDiagonal(position) || this.position.isColumnEquals(position) || this.position.isRowEquals(position);
    }

    private boolean isDiagonal(Position position) {
        return Math.abs(position.getColumnGap(this.position)) == Math.abs(position.getRowGap(this.position));
    }

    @Override
    public boolean isObstacle() {
        return true;
    }

    @Override
    public boolean hasToAlive() {
        return false;
    }
}
