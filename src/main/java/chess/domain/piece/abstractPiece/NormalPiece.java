package chess.domain.piece.abstractPiece;

import chess.domain.Movement;
import chess.domain.piece.character.Character;

public abstract class NormalPiece extends Piece {
    protected NormalPiece(Character character, boolean isMoved) {
        super(character, isMoved);
    }

    @Override
    public boolean isMovable(Movement movement, boolean isAttack) {
        return isMovable(movement.calculateRowDifference(), movement.calculateColumnDifference());
    }
}
