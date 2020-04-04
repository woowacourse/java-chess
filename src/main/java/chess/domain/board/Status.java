package chess.domain.board;

import chess.domain.player.PlayerColor;

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

    public boolean isWhiteTurn() {
        return PlayerColor.playerOf(turn) == PlayerColor.WHITE;
    }

    public boolean isBlackTurn() {
        return PlayerColor.playerOf(turn) == PlayerColor.BLACK;
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

    public String getMessage() {
        return String.format(statusType.getMessage(), getTurn(), PlayerColor.playerOf(turn).getName());
    }

    public int getTurn() {
        return turn;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return turn == status.turn &&
                statusType == status.statusType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn, statusType);
    }
}
