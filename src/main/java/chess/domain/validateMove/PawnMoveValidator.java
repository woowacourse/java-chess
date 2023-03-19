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
        if (validateData.getSourcePiece().getPieceType() != PieceType.PAWN) {
            setNext(new NotPawnAndKnightMoveValidator());
            return next.validate(validateData);
        }
        if (validateData.getSource().isSameFile(validateData.getTarget())) {
            return validateData.getChessboard().isEmptyInRoute(validateData.getSource(), validateData.getTarget()) &&
                    validateData.getChessboard().getPieceAt(validateData.getTarget()).getPieceType() == PieceType.EMPTY;
        }
        return validateData.getSourcePiece().isOpposite(validateData.getTargetPiece());
    }
}
