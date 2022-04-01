package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;
import chess.domain.piece.move.MovingStrategy;
import chess.domain.piece.move.pawn.PawnMovingStrategy;

public class Pawn extends Piece {

    private final MovingStrategy strategy;

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
        this.strategy = new PawnMovingStrategy(color);
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        return strategy.move(route, emptyPoints);
    }
}
