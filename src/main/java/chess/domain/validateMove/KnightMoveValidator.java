package chess.domain.validateMove;

import chess.domain.piece.PieceType;

public class KnightMoveValidator implements ValidateMove {
    private ValidateMove next;

    @Override
    public void setNext(final ValidateMove validateMove) {
        this.next = validateMove;
    }

    @Override
    public boolean validate(final ValidateData validateData) {
        if (validateData.isSourceNotTypeOf(PieceType.KNIGHT)) {
            setNext(new PawnMoveValidator());
            return next.validate(validateData);
        }
        return validateData.isNotSameCamp();
    }
}
