package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.List;

public class EmptyPiece extends Piece{
    private final static EmptyPiece INSTANCE = new EmptyPiece();

    private EmptyPiece() {
        super(TeamColor.NONE, PieceType.NONE);
    }

    public static EmptyPiece getInstance() {
        return INSTANCE;
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
    public boolean canMove(List<Piece> piecesOnRoute, Position start, Position target) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
