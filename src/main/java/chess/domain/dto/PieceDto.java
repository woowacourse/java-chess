package chess.domain.dto;

public class PieceDto {

    private String location;
    private String pieceKind;

    public PieceDto(String location, String pieceKind) {
        this.location = location;
        this.pieceKind = pieceKind;
    }

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
