package chess.domain.validateMove;

import chess.domain.piece.PieceType;

public class PawnMove implements ValidateMove {
    private ValidateMove next;

    @Override
    public void setNext(ValidateMove validateMove) {
        this.next = validateMove;
    }

    @Override
    public boolean validate(ValidateDto validateDto) {
        if (validateDto.getSourcePiece().getPieceType() != PieceType.PAWN) {
            setNext(new EmptyRoute());
            return next.validate(validateDto);
        }
        if (validateDto.getSource().isSameFile(validateDto.getTarget())) {
            return validateDto.getChessboard().isEmptyInRoute(validateDto.getSource(), validateDto.getTarget()) &&
                    validateDto.getChessboard().getPieceAt(validateDto.getTarget()).getPieceType() == PieceType.EMPTY;
        }
        return validateDto.getSourcePiece().isOpposite(validateDto.getTargetPiece());
    }
}
