package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.List;

public class Knight extends Piece {

    public Knight(final File file, final Rank rank, final Color color) {
        super(file, rank, color);
    }

    @Override
    protected boolean canMove(final Position targetPosition) {
        return false;
    }

    @Override
    public List<Position> getPassingPath(final Position targetPosition) {
        return null;
    }
}
