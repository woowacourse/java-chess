package chess.domain.board.piece;

import chess.domain.board.Position;
import chess.domain.board.Square;

public class Rook extends Piece{

    public Rook(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Square source, Square target) {
        if (!source.isStraight(target)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
