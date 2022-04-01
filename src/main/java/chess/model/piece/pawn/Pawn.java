package chess.model.piece.pawn;

import chess.model.Color;
import chess.model.piece.Piece;
import chess.model.piece.Point;
import chess.model.strategy.PawnMovableStrategy;

public class Pawn extends Piece {

    protected Pawn(Color color, PawnMovableStrategy strategy) {
        super(color, strategy);
    }

    public static Pawn of(Color color) {
        if (color.isBlack()) {
            return new BlackPawn();
        }
        return new WhitePawn();
    }

    @Override
    public double getPointValue() {
        return Point.PAWN.getValue();
    }
}
