package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {
    public Knight(Position position, Team team) {
        super(position, Name.KNIGHT, team);
    }


    @Override
    public boolean canNotMoveTo(Piece that) {
        return !createMovableArea().contains(that.position);
    }

    @Override
    protected List<Position> createMovableArea() {
        return Position.getPositions()
                .stream()
                .filter(this::isKnight)
                .collect(Collectors.toList());
    }

    private boolean isKnight(Position position) {
        return (Math.abs(position.getColumnGap(this.position)) == 2 && Math.abs(position.getRowGap(this.position)) == 1) ||
                (Math.abs(position.getColumnGap(this.position)) == 1 && Math.abs(position.getRowGap(this.position)) == 2);
    }
}
