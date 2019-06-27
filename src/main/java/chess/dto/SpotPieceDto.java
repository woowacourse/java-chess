package chess.dto;

public class SpotPieceDto {
    private String point;
    private String piece;

    public SpotPieceDto(String point, String piece) {
        this.point = point;
        this.piece = piece;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }
}
