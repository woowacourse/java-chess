package chess.domain.validateMove;

import chess.domain.piece.PieceType;

public class PawnMoveValidator implements ValidateMove {
    private ValidateMove next;

    @Override
    public void setNext(final ValidateMove validateMove) {
        this.next = validateMove;
    }

    @Override
    public boolean validate(final ValidateData validateData) {
        if (validateData.isSourceNotTypeOf(PieceType.PAWN)) {
            setNext(new EtcMoveValidator());
            return next.validate(validateData);
        }
        if (validateData.isSameFile()) {
            return isPossibleToMove(validateData);
        }
        return validateData.isOpposite();
    }

    private static boolean isPossibleToMove(final ValidateData validateData) {
        return validateData.isEmptyInRoute()
                && validateData.isTargetTypeOf(PieceType.EMPTY);
    }
}
