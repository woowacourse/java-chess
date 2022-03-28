package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.move.MovingStrategy;
import chess.domain.piece.move.knight.KnightMovingStrategy;

public class Knight extends Piece {

    private final MovingStrategy strategy;

    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
        this.strategy = new KnightMovingStrategy();
    }

    @Override
    public boolean move(Board ignored, Point from, Point to) {
        return strategy.move(ignored, from, to);
    }
}
