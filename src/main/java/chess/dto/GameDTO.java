package chess.dto;

public class GameDTO {

    private final String whiteUserName;
    private final String blackUserName;

    public GameDTO(String whiteUserName, String blackUserName) {
        this.whiteUserName = whiteUserName;
        this.blackUserName = blackUserName;
    }

    public String getBlackUserName() {
        return blackUserName;
    }

    public String getWhiteUserName() {
        return whiteUserName;
    }
}
