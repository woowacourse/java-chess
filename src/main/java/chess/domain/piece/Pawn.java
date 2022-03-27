package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.PawnMoveStrategy;

public final class Pawn extends Piece {

    public Pawn(Color color) {
        super(new Name("P"), color, new PawnMoveStrategy());
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        return moveStrategy.isValidateCanMove(color, targetPiece, from, to);
    }
}
