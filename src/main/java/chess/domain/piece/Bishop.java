package chess.domain.piece;

import static chess.domain.piece.Type.BISHOP;

import chess.domain.position.Position;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public Type identifyType() {
        return BISHOP;
    }

    @Override
    public boolean canMove(Position source, Position target, Piece piece) {
        if (piece.isSameColor(color)) {
            return false;
        }
        return isDiagonalMove(source, target);
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        return slidingMove(source, target, false);
    }
}
