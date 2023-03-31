package chess.view.resposne;

import chess.domain.game.state.StatusType;

public enum StatusMapper {
    START("ready", StatusType.START),
    PLAYING("playing", StatusType.PLAYING),
    END("end", StatusType.END);

    private final String status;
    private final StatusType statusType;

    StatusMapper(String status, StatusType statusType) {
        this.status = status;
        this.statusType = statusType;
    }

    public static String map(StatusType statusType) {
        for (StatusMapper statusMapper : values()) {
            if (statusMapper.statusType == statusType) {
                return statusMapper.status;
            }
        }
        throw new IllegalArgumentException("잘못된 status 입니다.");
    }
}
