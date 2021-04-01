package chess.dto;

public class UserIdsDto {

    private final String blackUserId;
    private final String whiteUserId;

    public UserIdsDto(final String blackUserId, final String whiteUserId) {
        this.blackUserId = blackUserId;
        this.whiteUserId = whiteUserId;
    }

    public String getBlackUserId() {
        return this.blackUserId;
    }

    public String getWhiteUserId() {
        return this.whiteUserId;
    }
}
