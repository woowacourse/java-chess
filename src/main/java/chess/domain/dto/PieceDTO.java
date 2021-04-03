package chess.domain.dto;

public class PieceDTO {

    public PieceDTO(String location, String pieceKind) {
        this.location = location;
        this.pieceKind = pieceKind;
    }

    private String location;
    private String pieceKind;

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPieceKind(String pieceKind) {
        this.pieceKind = pieceKind;
    }

    public String getLocation() {
        return location;
    }

    public String getPieceKind() {
        return pieceKind;
    }
}
