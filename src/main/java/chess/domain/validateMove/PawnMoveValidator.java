package chess.domain.validateMove;

import chess.domain.piece.PieceType;

public class PawnMoveValidator implements ValidateMove {
    private ValidateMove next;

    @Override
    public void setNext(ValidateMove validateMove) {
        this.next = validateMove;
    }

    @Override
    public boolean validate(ValidateData validateData) {
        if (validateData.isSourceNotTypeOf(PieceType.PAWN)) {
            setNext(new EtcMoveValidator());
            return next.validate(validateData);
        }
        if (validateData.isSameFile()) {
            return isPossibleToMove(validateData);
        }
        return validateData.isOpposite();
    }

    private static boolean isPossibleToMove(ValidateData validateData) {
        return validateData.isEmptyInRoute()
                && validateData.isTargetTypeOf(PieceType.EMPTY);
    }
}
