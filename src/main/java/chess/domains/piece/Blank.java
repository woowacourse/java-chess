package chess.domains.piece;

import chess.domains.position.Position;

import java.util.List;

public class Blank extends Piece {
    public Blank() {
        super(PieceColor.BLANK, ".", 0);
    }

    @Override
    public boolean isValidMove(Position currentPosition, Position targetPosition) {
        return false;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return null;
    }
}
