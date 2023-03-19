package chess.domain.validateMove;

public class SourceMove implements ValidateMove {
    private ValidateMove next;

    @Override
    public void setNext(ValidateMove validateMove) {
        this.next = validateMove;
    }

    @Override
    public boolean validate(ValidateData validateData) {
        if (validateData.getSourcePiece().canMove(validateData.getSource(), validateData.getTarget())) {
            setNext(new KnightMove());
            return next.validate(validateData);
        }
        return false;
    }
}
