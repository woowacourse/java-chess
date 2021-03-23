package chess.dto;

public class PiecesDTO {

    private final String pieces;

    public PiecesDTO(String pieces) {
        this.pieces = pieces;
    }

    public String getPieces() {
        return pieces;
    }
}
