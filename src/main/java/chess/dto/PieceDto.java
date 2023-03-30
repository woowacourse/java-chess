package chess.dto;

public class PieceDto {

    private final int id;
    private final int gameId;
    private final String type;
    private final String file;
    private final int rank;
    private final String color;

    private PieceDto(final int id, final int gameId, final String type, final String file, final int rank,
                    final String color) {
        this.id = id;
        this.gameId = gameId;
        this.type = type;
        this.file = file;
        this.rank = rank;
        this.color = color;
    }

    public static PieceDto of(final int gameId, final String type, final String file, final int rank,
                              final String color) {
        return new PieceDto(0, gameId, type, file, rank, color);
    }

    public static PieceDto from(final int id) {
        return new PieceDto(id, 0, null, null, 0, null);
    }

    public static PieceDto of(final int id, final String file, final int rank) {
        return new PieceDto(id, 0, null, file, rank, null);
    }

    public int getId() {
        return id;
    }

    public int getGameId() {
        return gameId;
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
