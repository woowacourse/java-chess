package chess.dto;

public final class UsersDTO {
    private final String blackUser;
    private final String whiteUser;

    public UsersDTO(String blackUser, String whiteUser) {
        this.blackUser = blackUser;
        this.whiteUser = whiteUser;
    }

    public String getBlackUser() {
        return blackUser;
    }

    public String getWhiteUser() {
        return whiteUser;
    }
}
