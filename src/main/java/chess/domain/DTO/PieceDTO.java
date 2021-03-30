package chess.domain.DTO;

public class PieceDTO {

    String piece;

    public PieceDTO(String piece) {
        this.piece = piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getPiece() {
        return piece;
    }
}
