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
}
