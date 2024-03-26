package exception;

import constant.ErrorCode;

public class PieceExistInRouteException extends CustomException {

    public PieceExistInRouteException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
