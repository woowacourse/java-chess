package chessgame.dto;

public class PieceDto {

    private final int rank;
    private final int file;
    private final String type;
    private final String camp;
    private final long gameRoomId;

    public PieceDto(int rank, int file, String type, String camp, long gameRoomId) {
        this.rank = rank;
        this.file = file;
        this.type = type;
        this.camp = camp;
        this.gameRoomId = gameRoomId;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    public String getType() {
        return type;
    }

    public String getCamp() {
        return camp;
    }

    public long getGameRoomId() {
        return gameRoomId;
    }
}
