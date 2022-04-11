package chess.dto;

public class GameDto {

    private final String whiteUserName;
    private final String blackUserName;
    private final String state;

    public GameDto(String whiteUserName, String blackUserName, String state) {
        this.whiteUserName = whiteUserName;
        this.blackUserName = blackUserName;
        this.state = state;
    }

    public String getWhiteUserName() {
        return whiteUserName;
    }

    public String getBlackUserName() {
        return blackUserName;
    }

    public String getState() {
        return state;
    }
}
