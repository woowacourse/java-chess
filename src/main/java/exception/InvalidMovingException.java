package exception;

import constant.ErrorCode;

public class InvalidMovingException extends CustomException {

    public InvalidMovingException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
