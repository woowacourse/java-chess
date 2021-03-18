package chess.domain.piece;

import chess.domain.board.position.Position;

public class Queen extends Piece{

    public Queen(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Position source, Position target, Piece targetPiece) {
        if(!(source.isStraight(target) || source.isDiagonal(target))){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
