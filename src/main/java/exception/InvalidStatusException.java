package exception;

import constant.ErrorCode;

public class InvalidStatusException extends CustomException {

    public InvalidStatusException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
