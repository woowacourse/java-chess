package chess.domain.board.piece;

import chess.domain.board.Position;
import chess.domain.board.Square;

public class Bishop extends Piece {
    public Bishop(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Square source, Square target) {
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
