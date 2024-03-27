package chess.domain.piece;

import static chess.domain.piece.Type.ROOK;

import chess.domain.position.Position;

import java.util.List;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public Type identifyType() {
        return ROOK;
    }

    @Override
    public boolean canMove(Position source, Position target, Piece piece) {
        if (piece.isSameColor(color)) {
            return false;
        }
        return isVerticalMove(source, target) || isHorizontalMove(source, target);
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        return slidingMove(source, target, isVerticalMove(source, target));
    }
}
