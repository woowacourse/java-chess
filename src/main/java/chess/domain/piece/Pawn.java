package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.move.MovingStrategy;
import chess.domain.piece.move.pawn.PawnMovingStrategy;

public class Pawn extends Piece {

    private final MovingStrategy strategy;

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
        this.strategy = new PawnMovingStrategy(color);
    }

    @Override
    public boolean move(Board board, Point from, Point to) {
        return strategy.move(board, from, to);
    }
}
