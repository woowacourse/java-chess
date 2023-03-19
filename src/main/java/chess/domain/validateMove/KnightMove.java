package chess.domain.validateMove;

import chess.domain.piece.PieceType;

public class KnightMove implements ValidateMove {
    private ValidateMove next;

    @Override
    public void setNext(ValidateMove validateMove) {
        this.next = validateMove;
    }

    @Override
    public boolean validate(ValidateData validateData) {
        if (validateData.getSourcePiece().getPieceType() != PieceType.KNIGHT) {
            setNext(new PawnMove());
            return next.validate(validateData);
        }
        return validateData.getSourcePiece().isNotSameCamp(validateData.getTargetPiece());
    }
}
