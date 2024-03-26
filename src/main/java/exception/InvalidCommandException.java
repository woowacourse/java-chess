package exception;

import constant.ErrorCode;

public class InvalidCommandException extends CustomException {

    public InvalidCommandException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
