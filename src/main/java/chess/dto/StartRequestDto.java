package chess.dto;

public class StartRequestDto {

    private final String gameName;
    private final String blackUser;
    private final String whiteUser;

    public StartRequestDto(final String gameName, final String blackUser, final String whiteUser) {
        this.gameName = gameName;
        this.blackUser = blackUser;
        this.whiteUser = whiteUser;
    }

    public String getGameName() {
        return gameName;
    }

    public String getBlackUser() {
        return blackUser;
    }

    public String getWhiteUser() {
        return whiteUser;
    }
}
