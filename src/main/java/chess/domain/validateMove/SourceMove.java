package chess.domain.validateMove;

public class SourceMove implements ValidateMove{
    private ValidateMove next;
    @Override
    public void setNext(ValidateMove validateMove) {
        this.next = validateMove;
    }

    @Override
    public boolean validate(ValidateDto validateDto) {
        if(validateDto.getSourcePiece().canMove(validateDto.getSource(),validateDto.getTarget())){
            setNext(new KnightMove());
            return next.validate(validateDto);
        }
        return false;
    }
}
