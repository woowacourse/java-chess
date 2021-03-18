package chess.domain.piece;

import chess.domain.board.position.Position;

public class Bishop extends Piece {
    public Bishop(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Position source, Position target, Piece targetPiece) {
        if(!source.isDiagonal(target)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
