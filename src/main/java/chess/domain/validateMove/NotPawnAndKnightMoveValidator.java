package chess.domain.validateMove;

public class NotPawnAndKnightMoveValidator implements ValidateMove {
    @Override
    public void setNext(ValidateMove validateMove) {
    }

    @Override
    public boolean validate(ValidateData validateData) {
        if (validateData.isEmptyInRoute()) {
            return validateData.isNotSameCamp();
        }
        return false;
    }
}
