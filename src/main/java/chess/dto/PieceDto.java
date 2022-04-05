package chess.dto;

public class PieceDto {
    private final String type;
    private final String camp;

    public PieceDto(String type, String camp) {
        this.type = type;
        this.camp = camp;
    }

    public static PieceDto of(String type, boolean isWhite) {
        if (isWhite) {
            return new PieceDto(type, "white");
        }
        return new PieceDto(type, "black");
    }

    public String getType() {
        return type;
    }

    public String getCamp() {
        return camp;
    }
}
