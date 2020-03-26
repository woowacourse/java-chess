package chess.domains.piece;

import chess.domains.position.Position;

import java.util.List;

public class Blank extends Piece {
    public Blank() {
        super(PieceColor.BLANK, ".");
    }

    @Override
    public boolean canMove(Position currentPosition, Position targetPosition) {
        return false;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return null;
    }
}
