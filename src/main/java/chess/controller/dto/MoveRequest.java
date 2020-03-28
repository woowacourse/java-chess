package chess.controller.dto;

public class MoveRequest {
    private final String file;
    private final String rank;

    public MoveRequest(String file, String rank) {
        this.file = file;
        this.rank = rank;
    }

    public String getFile() {
        return file;
    }

    public String getRank() {
        return rank;
    }
}
