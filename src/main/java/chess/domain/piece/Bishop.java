package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.strategy.BishopMoveStrategy;
import chess.domain.piece.strategy.MoveStrategy;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(new Name("B"), color);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {
        MoveStrategy moveStrategy = new BishopMoveStrategy();
        moveStrategy.canMove(color, from, to);
    }
}
