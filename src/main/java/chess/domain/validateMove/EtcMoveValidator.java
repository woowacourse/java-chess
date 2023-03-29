package chess.domain.validateMove;

public class EtcMoveValidator implements ValidateMove {
    @Override
    public void setNext(final ValidateMove validateMove) {
    }

    @Override
    public boolean validate(final ValidateData validateData) {
        if (validateData.isEmptyInRoute()) {
            return validateData.isNotSameCamp();
        }
        return false;
    }
}
