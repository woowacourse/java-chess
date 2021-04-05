package chess.dto.web;

public class UsersInRoomDto {

    private final String whiteName;
    private final String whiteWin;
    private final String whiteLose;
    private final String blackName;
    private final String blackWin;
    private final String blackLose;

    public UsersInRoomDto(String whiteName, String whiteWin, String whiteLose, String blackName,
        String blackWin, String blackLose) {
        this.whiteName = whiteName;
        this.whiteWin = whiteWin;
        this.whiteLose = whiteLose;
        this.blackName = blackName;
        this.blackWin = blackWin;
        this.blackLose = blackLose;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public String getWhiteWin() {
        return whiteWin;
    }

    public String getWhiteLose() {
        return whiteLose;
    }

    public String getBlackName() {
        return blackName;
    }

    public String getBlackWin() {
        return blackWin;
    }

    public String getBlackLose() {
        return blackLose;
    }
}
