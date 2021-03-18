package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;

public class RealPiece extends Piece{
    private final Color color;

    public RealPiece(Color color, String notation, MoveStrategy moveStrategy) {
        super(notation, moveStrategy);
        this.color = color;
    }

    @Override
    public String getNotation() {
        return color.changeNotation(super.getNotation());
    }
}
