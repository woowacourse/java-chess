package exception;

import constant.ErrorCode;

public class InvalidInputException extends CustomException {

    public InvalidInputException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
