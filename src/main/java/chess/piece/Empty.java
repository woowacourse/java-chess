package chess.piece;

import chess.Position;
import chess.Team;

public class Empty extends Piece{

    private static final String NAME = ".";

    public Empty(Position position) {
        super(position, Team.NONE);
    }

    @Override
    public boolean isMovable(Position position) {
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
