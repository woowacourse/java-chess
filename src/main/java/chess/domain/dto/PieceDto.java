package chess.domain.dto;

public class PieceDto {
    private String position;
    private String piece;

    public PieceDto(String position, String piece) {
        this.position = position;
        this.piece = piece;
    }

    public String getPosition() {
        return position;
    }

    public String getPiece() {
        return piece;
    }
}
