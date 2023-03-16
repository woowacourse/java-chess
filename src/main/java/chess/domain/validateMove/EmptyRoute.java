package chess.domain.validateMove;

public class EmptyRoute implements ValidateMove {
    @Override
    public void setNext(ValidateMove validateMove) {
    }

    @Override
    public boolean validate(ValidateDto validateDto) {
        if (validateDto.getChessboard().isEmptyInRoute(validateDto.getSource(), validateDto.getTarget())) {
            return validateDto.getSourcePiece().isNotSameCamp(validateDto.getTargetPiece());
        }
        return false;
    }
}
