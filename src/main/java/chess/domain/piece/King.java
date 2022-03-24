package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.strategy.KingMoveStrategy;
import chess.domain.piece.strategy.MoveStrategy;

public class King extends Piece {
    public King(Color color) {
        super(new Name("K"), color);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {
        MoveStrategy moveStrategy = new KingMoveStrategy();
        moveStrategy.canMove(color, from, to);
    }
}
