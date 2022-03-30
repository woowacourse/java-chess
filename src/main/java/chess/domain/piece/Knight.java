package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;
import chess.domain.piece.move.MovingStrategy;
import chess.domain.piece.move.knight.KnightMovingStrategy;

public class Knight extends Piece {

    private final MovingStrategy strategy;

    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
        this.strategy = new KnightMovingStrategy();
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        return strategy.move(route, emptyPoints);
    }
}
