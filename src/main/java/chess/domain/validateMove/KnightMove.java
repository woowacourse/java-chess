package chess.domain.validateMove;

import chess.domain.piece.PieceType;

public class KnightMove implements ValidateMove {
    private ValidateMove next;

    @Override
    public void setNext(ValidateMove validateMove) {
        this.next = validateMove;
    }

    @Override
    public boolean validate(ValidateDto validateDto) {
        if (validateDto.getSourcePiece().getPieceType() != PieceType.KNIGHT) {
            setNext(new PawnMove());
            return next.validate(validateDto);
        }
        if (validateDto.getSourcePiece().isNotSameCamp(validateDto.getTargetPiece())) {
            return true;
        }
        return false;
    }
}
