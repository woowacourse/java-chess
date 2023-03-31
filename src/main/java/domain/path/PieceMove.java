package domain.path;

import domain.path.location.Location;

public final class PieceMove {

    private final Location start;
    private final Location end;

    public PieceMove(final Location start, final Location end) {
        this.start = start;
        this.end = end;
    }

    public Location getStart() {
        return start;
    }

    public Location getEnd() {
        return end;
    }

    public int getRowDiff() {
        return start.getRow() - end.getRow();
    }

    public int getColumnDiff() {
        return start.getCol() - end.getCol();
    }
}
