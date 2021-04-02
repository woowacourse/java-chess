package chess.dto;

public class RoomUsersDto {

    private String whiteName;
    private String whiteWin;
    private String whiteLose;
    private String blackName;
    private String blackWin;
    private String blackLose;

    public RoomUsersDto(String whiteName, String whiteWin, String whiteLose, String blackName,
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
