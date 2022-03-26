package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.KingMoveStrategy;

public class King extends Piece {
    public King(Color color) {
        super(new Name("K"), color, new KingMoveStrategy());
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        return moveStrategy.isValidateCanMove(color, from, to);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
