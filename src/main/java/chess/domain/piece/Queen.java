package chess.domain.piece;

import static chess.domain.piece.Type.QUEEN;

import chess.domain.position.Position;

import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public Type identifyType() {
        return QUEEN;
    }

    @Override
    public boolean canMove(Position source, Position target, Piece piece) {
        if (piece.isSameColor(color)) {
            return false;
        }
        return isDiagonalMove(source, target) || isHorizontalMove(source, target) || isVerticalMove(source, target);
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        return slidingMove(source, target, isVerticalMove(source, target));
    }
}
