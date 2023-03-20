package chess.domain.validateMove;

import chess.domain.piece.PieceType;

public class KnightMoveValidator implements ValidateMove {
    private ValidateMove next;

    @Override
    public void setNext(ValidateMove validateMove) {
        this.next = validateMove;
    }

    @Override
    public boolean validate(ValidateData validateData) {
        if (validateData.isSourceNotTypeOf(PieceType.KNIGHT)) {
            setNext(new PawnMoveValidator());
            return next.validate(validateData);
        }
        return validateData.isNotSameCamp();
    }
}
