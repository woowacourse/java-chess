package chess.domains.piece;

import chess.domains.position.Position;

import java.util.List;

public class Rook extends Piece {
    public Rook(PieceColor pieceColor) {
        super(pieceColor, "r", 5);
    }

    @Override
    public boolean isValidMove(Position current, Position target) {
        return current.isSameX(target) || current.isSameY(target);
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return source.findRoute(target);
    }
}
