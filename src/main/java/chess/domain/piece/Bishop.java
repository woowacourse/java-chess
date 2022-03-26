package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.BishopMoveStrategy;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(new Name("B"), color, new BishopMoveStrategy());
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        return moveStrategy.isValidateCanMove(color, from, to);
    }
}
