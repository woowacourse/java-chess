package chess.dto;

public class SquareDto {

    private String position;
    private String piece;

    public SquareDto(String position, String piece) {
        this.position = position;
        this.piece = piece;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }
}
