package chess.domain.board;

public class Status {

    private static final int INIT_TURN = 0;
    public static final Status READY_STATUS = new Status(INIT_TURN, StatusType.READY);
    public static final Status INITIAL_STATUS = new Status(INIT_TURN, StatusType.PROCESSING);

    private final int turn;
    private final StatusType statusType;

    public Status(int turn, StatusType statusType) {
        this.turn = turn;
        this.statusType = statusType;
    }

    public boolean isWhiteTurn() {
        return turn % 2 == 0;
    }

    public boolean isBlackTurn() {
        return !isWhiteTurn();
    }

    public boolean isNotProcessing() {
        return !statusType.equals(StatusType.PROCESSING);
    }

    public boolean isNotFinished() {
        return !statusType.equals(StatusType.FINISHED);
    }

    public Status nextTurn() {
        return new Status(turn + 1, statusType);
    }

    public Status finish() {
        return new Status(turn, StatusType.FINISHED);
    }

    public int getTurn() {
        return turn;
    }

    public StatusType getStatusType() {
        return statusType;
    }
}
