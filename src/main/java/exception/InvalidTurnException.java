package exception;

import constant.ErrorCode;

public class InvalidTurnException extends CustomException {

    public InvalidTurnException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
