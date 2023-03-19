package chess.domain.validateMove;

public class EmptyRoute implements ValidateMove {
    @Override
    public void setNext(ValidateMove validateMove) {
    }

    @Override
    public boolean validate(ValidateData validateData) {
        if (validateData.getChessboard().isEmptyInRoute(validateData.getSource(), validateData.getTarget())) {
            return validateData.getSourcePiece().isNotSameCamp(validateData.getTargetPiece());
        }
        return false;
    }
}
