package chess.direction;

import chess.File;
import chess.Position;
import chess.Rank;

public class Route {

    private final int y;
    private final int x;

    public Route(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Position createPositionFrom(Rank rank, File file) {
        return Position.of(rank.add(y), file.add(x));
    }

    public boolean canCreatePosition(Rank rank, File file) {
        return rank.canAdd(y) && file.canAdd(x);
    }
}
