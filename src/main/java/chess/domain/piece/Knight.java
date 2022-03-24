package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.strategy.KnightMoveStrategy;
import chess.domain.piece.strategy.MoveStrategy;

public class Knight extends Piece {
    public Knight(Color color) {
        super(new Name("N"), color);
    }


    @Override
    public void canMove(Board board, Position from, Position to) {
        MoveStrategy moveStrategy = new KnightMoveStrategy();
        moveStrategy.canMove(color, from, to);

    }
}
