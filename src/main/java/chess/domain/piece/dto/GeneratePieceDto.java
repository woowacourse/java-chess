package chess.domain.piece.dto;

public class GeneratePieceDto {

    private String rank;
    private String file;
    private String type;
    private String side;

    public GeneratePieceDto(String rank, String file, String type, String side) {
        this.rank = rank;
        this.file = file;
        this.type = type;
        this.side = side;
    }

    public String getRank() {
        return rank;
    }

    public String getFile() {
        return file;
    }

    public String getType() {
        return type;
    }

    public String getSide() {
        return side;
    }
}
