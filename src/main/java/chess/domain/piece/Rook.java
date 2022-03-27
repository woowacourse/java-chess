package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.RookMoveStrategy;

public final class Rook extends Piece {
    public Rook(Color color) {
        super(new Name("R"), color, new RookMoveStrategy());
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        return moveStrategy.isValidateCanMove(color, from, to);
    }
}
