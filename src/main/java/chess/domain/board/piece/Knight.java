package chess.domain.board.piece;

import chess.domain.board.Position;

public class Knight extends Piece{
    public Knight(Owner owner) {
        super(owner);
    }

    @Override
    public boolean isValidMove(Position source, Position target) {
        return false;
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "N";
    }
}
