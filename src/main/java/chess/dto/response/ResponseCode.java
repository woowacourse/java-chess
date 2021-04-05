package chess.dto.response;

public enum ResponseCode {
    OK(200, "데이터를 조회하는 데 성공했습니다."),
    NO_CONTENT(204, "요청에 대한 처리가 성공했습니다. 그리고 응답할 컨텐츠는 존재하지 않습니다."),
    WRONG_ARGUMENTS(401, "잘못된 값을 입력했습니다."),
    WRONG_ARGUMENTS_INSERT_ERROR(402, "값을 잘못 입력하여 아무 값도 삽입되지 않았습니다."),
    NOT_EXISTING_ROW(403, "해당하는 이름의 Row가 존재하지 않습니다."),
    NOT_EXISTING_PIECE(404, "입력 값에 해당하는 말이 없습니다."),
    SERVER_ERROR(500, "서버 내부의 에러입니다. 관리자에게 요청해주세요."),
    PAWN_TWO_STEP_MOVE(600, "폰은 초기 자리에서만 두칸 이동 가능합니다."),
    PAWN_DIAGONAL_MOVE(601, "폰은 상대 말을 먹을 때만 대각선으로 이동이 가능합니다."),
    PAWN_FORWARD_MOVE(602, "폰은 한칸 앞 말이 있으면 가지 못합니다."),
    EMPTY_CANNOT_MOVE(603, "빈 공간을 옮길 수 없습니다."),
    CANNOT_MOVE_POSITION(604, "이동할 수 없는 위치입니다."),
    MY_PIECE_MOVE(605, "자신의 말만 옮길 수 있습니다."),
    GAME_OVER(700, "이미 게임이 끝났습니다."),
    GAME_START(701, "이미 게임이 시작했습니다."),
    GAME_NOT_START(702, "아직 게임이 시작되지 않았습니다.");


    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
