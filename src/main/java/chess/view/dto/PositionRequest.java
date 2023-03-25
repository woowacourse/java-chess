package chess.view.dto;

public class PositionRequest {

    private final String file;
    private final String rank;

    public PositionRequest(String file, String rank) {
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
