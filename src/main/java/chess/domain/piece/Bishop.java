package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.BishopMoveStrategy;

public final class Bishop extends Piece {

    public Bishop(Color color) {
        super(new Name("B"), color, new BishopMoveStrategy());
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        return moveStrategy.isValidateCanMove(color, from, to);
    }
}
