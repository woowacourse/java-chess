package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.piece.strategy.RookMoveStrategy;

public class Rook extends Piece {
    public Rook(Color color) {
        super(new Name("R"), color);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {
        MoveStrategy moveStrategy = new RookMoveStrategy();
        moveStrategy.canMove(color, from, to);
    }
}
