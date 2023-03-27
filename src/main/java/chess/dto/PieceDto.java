package chess.dto;

public class PieceDto {

    private final int runningGameId;
    private final String type;
    private final String file;
    private final int rank;
    private final String color;

    public PieceDto(final int runningGameId, final String type, final String file, final int rank, final String color) {
        this.runningGameId = runningGameId;
        this.type = type;
        this.file = file;
        this.rank = rank;
        this.color = color;
    }

    public int getRunningGameId() {
        return runningGameId;
    }

    public String getType() {
        return type;
    }

    public String getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public String getColor() {
        return color;
    }
}
