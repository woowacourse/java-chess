package chess.domain.validateMove;

public interface ValidateMove {
    void setNext(ValidateMove validateMove);

    boolean validate(ValidateData validateData);
}
