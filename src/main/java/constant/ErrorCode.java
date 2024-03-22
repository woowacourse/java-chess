package constant;

public enum ErrorCode {

    // TODO 전체적으로 네이밍 다시 고민해보기
    INVALID_INPUT,
    INVALID_STATUS,
    INVALID_COMMAND,
    INVALID_POSITION,
    INVALID_MOVEMENT_RULE,
    PIECE_EXIST_IN_ROUTE,
    PIECE_DOES_NOT_EXIST_POSITION,
    INVALID_CAMP_PIECE, // todo 다른 진영의 말 움직이려 할때 하는 예외 코드 네이밍 고민해보기
    NO_MESSAGE,
    ;

}
