package chess.domain.validateMove;

public class SourceMoveValidator implements ValidateMove {
    private ValidateMove next;

    @Override
    public void setNext(ValidateMove validateMove) {
        this.next = validateMove;
    }

    @Override
    public boolean validate(ValidateData validateData) {
        if (validateData.canMove()) {
            setNext(new KnightMoveValidator());
            return next.validate(validateData);
        }
        return false;
    }
}
