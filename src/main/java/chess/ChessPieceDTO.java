package chess;

public class ChessPieceDTO {
    private String position;
    private String piece;

    public ChessPieceDTO(String position, String piece) {
        this.position = position;
        this.piece = piece;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getPosition() {
        return position;
    }

    public String getPiece() {
        return piece;
    }
}
