package chess.web.service;

public class PieceDto {
    private String type;
    private String color;

    public PieceDto(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }
}
