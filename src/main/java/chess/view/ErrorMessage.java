package chess.view;

public enum ErrorMessage {

    MOVE_FORWARD_ERROR_GUIDE_MESSAGE("도착점에 기물이 있어 앞으로 이동할 수 없습니다"),
    MOVE_DIAGONAL_ERROR_GUIDE_MESSAGE("Pawn은 대각선에 상대방이 있을 때만 이동할 수 있습니다"),
    MOVE_DISTANCE_ERROR_GUIDE_MESSAGE("한 번에 이동할 수 있는 거리가 아닙니다"),
    MOVE_DIRECTION_ERROR_GUIDE_MESSAGE("이동할 수 있는 방향이 아닙니다"),
    EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE("목적지에 아군이 있으므로 이동할 수 없습니다."),
    OTHER_PIECE_IN_ROUTE_ERROR_GUIDE_MESSAGE("이동 경로에 기물이 있으므로 이동할 수 없습니다"),
    FIRST_COMMAND_ERROR_MESSAGE("첫 번째로는 start, end만 입력 가능합니다"),
    NO_PIECE_ERROR_MESSAGE("이동할 수 있는 기물이 없습니다");


    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
