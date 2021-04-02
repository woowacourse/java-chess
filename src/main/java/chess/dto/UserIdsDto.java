package chess.dto;

public class UserIdsDto {

    private final String whiteUserId;
    private final String blackUserId;

    public UserIdsDto(String whiteUserId, String blackUserId) {
        this.whiteUserId = whiteUserId;
        this.blackUserId = blackUserId;
    }

    public String getWhiteUserId() {
        return whiteUserId;
    }

    public String getBlackUserId() {
        return blackUserId;
    }
}
