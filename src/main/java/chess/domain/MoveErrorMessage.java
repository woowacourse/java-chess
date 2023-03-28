package chess.domain;

public class MoveErrorMessage {

    public static final String WRONG_START_ERROR_MESSAGE = "시작 위치에 말이 없습니다.";
    public static final String WRONG_PIECE_COLOR_ERROR_MESSAGE = "자신 팀의 말만 이동시킬 수 있습니다.";
    public static final String OBSTACLE_IN_PATH_ERROR_MESSAGE = "경로에 다른 말이 있어서 이동할 수 없습니다.";
    public static final String WRONG_ATTACK_ERROR_MESSAGE = "선택한 말로 공격할 수 없습니다.";
    public static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";

}
