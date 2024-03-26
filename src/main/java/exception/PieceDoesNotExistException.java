package exception;

import constant.ErrorCode;

public class PieceDoesNotExistException extends CustomException {

    public PieceDoesNotExistException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
