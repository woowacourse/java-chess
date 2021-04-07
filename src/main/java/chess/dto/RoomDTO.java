package chess.dto;

public final class RoomDTO {
    private final int id;
    private final String title;
    private final String blackUser;
    private final String whiteUser;
    private final String status;
    private final boolean playing;

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
