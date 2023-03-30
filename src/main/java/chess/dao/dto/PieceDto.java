package chess.dao.dto;

public class PieceDto {

    private final Long id;
    private final Long chessGameId;
    private final String color;
    private final String type;
    private final String file;
    private final String rank;

    private PieceDto(
            final Long id, final Long chessGameId, final String color,
            final String type, final String file, final String rank
    ) {
        this.id = id;
        this.chessGameId = chessGameId;
        this.color = color;
        this.type = type;
        this.file = file;
        this.rank = rank;
    }

    public static PieceDto of(
            final Long id, final Long chessGameId, final String color,
            final String type, final String file, final String rank
    ) {
        return new PieceDto(id, chessGameId, color, type, file, rank);
    }

    public Long getId() {
        return id;
    }

    public Long getChessGameId() {
        return chessGameId;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public String getFile() {
        return file;
    }

    public String getRank() {
        return rank;
    }
}
