package chess.domain.board;

import java.util.Objects;

public class Status {

    private static final int INIT_TURN = 0;

    private final int turn;
    private final StatusType statusType;

    public Status(int turn, StatusType statusType) {
        this.turn = turn;
        this.statusType = statusType;
    }

    public static Status readyStatus() {
        return new Status(INIT_TURN, StatusType.READY);
    }

    public static Status initialStatus() {
        return new Status(INIT_TURN, StatusType.PROCESSING);
    }

    public static Status from(int turn) {
        return new Status(turn, StatusType.PROCESSING);
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Status))
            return false;
        Status status = (Status)o;
        return turn == status.turn &&
                statusType == status.statusType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn, statusType);
    }
}
