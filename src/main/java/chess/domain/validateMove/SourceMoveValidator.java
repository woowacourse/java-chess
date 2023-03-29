package chess.domain.validateMove;

public class SourceMoveValidator implements ValidateMove {
    private ValidateMove next;

    @Override
    public void setNext(final ValidateMove validateMove) {
        this.next = validateMove;
    }

    @Override
    public boolean validate(final ValidateData validateData) {
        if (validateData.canMove()) {
            setNext(new KnightMoveValidator());
            return next.validate(validateData);
        }
        return false;
    }
}
