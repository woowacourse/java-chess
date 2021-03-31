package chess.dto;

public class RoomDTO {
    private int id;
    private String title;
    private String blackUser;
    private String whiteUser;
    private String status;
    private boolean playing;

    public RoomDTO(int id, String title, String blackUser, String whiteUser, int status, boolean playing) {
        this.id = id;
        this.title = title;
        this.blackUser = blackUser;
        this.whiteUser = whiteUser;
        this.status = status(status);
        this.playing = playing;
    }

    private String status(int status) {
        if (status == 1) {
            return "진행중";
        }
        return "종료됨";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBlackUser() {
        return blackUser;
    }

    public String getWhiteUser() {
        return whiteUser;
    }

    public String getStatus() {
        return status;
    }

    public boolean isPlaying() {
        return playing;
    }
}
