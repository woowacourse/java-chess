package chess.domain.piece;

import chess.domain.board.position.Position;

public class Knight extends Piece{
    public Knight(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Position source, Position target, Piece targetPiece) {
        int horizontalDifferent = source.getHorizontalDistance(target);
        int verticalDifferent = source.getVerticalDistance(target);

        if(!((horizontalDifferent == 2 && verticalDifferent == 1)
                || (horizontalDifferent ==1 && verticalDifferent ==2))){
            throw new IllegalArgumentException();
        }
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
