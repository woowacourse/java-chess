package exception;

import constant.ErrorCode;

public class NoMessageException extends CustomException {

    public NoMessageException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
