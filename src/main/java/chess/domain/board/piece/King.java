package chess.domain.board.piece;

import chess.domain.board.Position;

public class King extends Piece{

    public King(Owner owner) {
        super(owner);
    }

    @Override
    public boolean isValidMove(Position source, Position target) {
        return false;
    }

    @Override
    public Score score() {
        return new Score(0);
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
