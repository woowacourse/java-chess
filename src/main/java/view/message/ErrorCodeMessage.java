package view.message;

import constant.ErrorCode;
import exception.MessageDoesNotExistException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ErrorCodeMessage {

    INVALID_INPUT(ErrorCode.INVALID_INPUT, "값을 입력해주세요"),
    INVALID_STATUS(ErrorCode.INVALID_STATUS, "유효하지 않은 상태입니다."),
    INVALID_COMMAND(ErrorCode.INVALID_COMMAND, "유효하지 않은 명령입니다."),
    INVALID_POSITION(ErrorCode.INVALID_POSITION, "유효하지 않은 위치입니다."),
    INVALID_PAWN_MOVEMENT(ErrorCode.INVALID_PAWN_MOVEMENT, "유효하지 않은 폰의 이동 경로입니다."),
    INVALID_KING_MOVEMENT(ErrorCode.INVALID_KING_MOVEMENT, "유효하지 않은 킹의 이동 경로입니다."),
    INVALID_BISHOP_MOVEMENT(ErrorCode.INVALID_BISHOP_MOVEMENT, "유효하지 않은 비숍의 이동 경로입니다."),
    INVALID_KNIGHT_MOVEMENT(ErrorCode.INVALID_KNIGHT_MOVEMENT, "유효하지 않은 나이트의 이동 경로입니다."),
    INVALID_QUEEN_MOVEMENT(ErrorCode.INVALID_QUEEN_MOVEMENT, "유효하지 않은 퀸의 이동 경로입니다."),
    INVALID_ROOK_MOVEMENT(ErrorCode.INVALID_ROOK_MOVEMENT, "유효하지 않은 룩의 이동 경로입니다."),
    PIECE_EXIST_IN_ROUTE(ErrorCode.PIECE_EXIST_IN_ROUTE, "이동 경로에 기물이 있습니다."),
    OWN_PIECE_EXIST_POSITION(ErrorCode.OWN_PIECE_EXIST_POSITION, "도착 위치에 자신의 기물이 있습니다."),
    PIECE_DOES_NOT_EXIST_POSITION(ErrorCode.PIECE_DOES_NOT_EXIST_POSITION, "해당 위치에 기물이 없습니다."),
    INVALID_CAMP_PIECE(ErrorCode.INVALID_CAMP_PIECE, "자신의 기물만 움직일 수 있습니다."),
    NO_MESSAGE(ErrorCode.NO_MESSAGE, "해당 메시지가 없습니다.");

    private static final Map<ErrorCode, ErrorCodeMessage> SUIT_MESSAGE = Arrays.stream(values())
            .collect(Collectors.toMap(ErrorCodeMessage::getCode, Function.identity()));

    private final ErrorCode errorCode;
    private final String message;

    ErrorCodeMessage(final ErrorCode errorCode, final String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static ErrorCodeMessage from(final ErrorCode errorCode) {
        if (SUIT_MESSAGE.containsKey(errorCode)) {
            return SUIT_MESSAGE.get(errorCode);
        }
        throw new MessageDoesNotExistException(ErrorCode.NO_MESSAGE);
    }

    public String getMessage() {
        return message;
    }

    private ErrorCode getCode() {
        return errorCode;
    }
}
