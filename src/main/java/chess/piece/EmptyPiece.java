package chess.piece;

import chess.Position;
import chess.TeamColor;
import java.util.List;

public class EmptyPiece extends Piece{
    public EmptyPiece() {
        super(TeamColor.NONE);
    }

    @Override
    public boolean availablePath(Position start, Position target) {
        return false;
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {
        return null;
    }

    @Override
    public boolean canMove(Piece targetPiece, Position start, Position target) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
