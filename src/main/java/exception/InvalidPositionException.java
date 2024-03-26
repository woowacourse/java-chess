package exception;

import constant.ErrorCode;

public class InvalidPositionException extends CustomException {

    public InvalidPositionException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
