package chess.dto;

public class ChessDTO {

    private final String color;
    private final String piece;
    private final String position;

    public ChessDTO(String color, String piece, String position) {
        this.color = color;
        this.piece = piece;
        this.position = position;
    }

    public String getPiece() {
        return piece.toLowerCase();
    }

    public String getPosition() {
        return position;
    }

    public String getColor() {
        return color.toLowerCase();
    }
}
