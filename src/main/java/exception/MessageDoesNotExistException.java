package exception;

import constant.ErrorCode;

public class MessageDoesNotExistException extends CustomException {

    public MessageDoesNotExistException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
