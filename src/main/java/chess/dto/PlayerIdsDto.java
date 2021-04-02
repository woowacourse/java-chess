package chess.dto;

public class PlayerIdsDto {

    private final String whiteUserId;
    private final String blackUserId;

    public PlayerIdsDto(final String whiteUserId, final String blackUserId) {
        this.whiteUserId = whiteUserId;
        this.blackUserId = blackUserId;
    }

    public String getBlackUserId() {
        return blackUserId;
    }

    public String getWhiteUserId() {
        return whiteUserId;
    }
}
