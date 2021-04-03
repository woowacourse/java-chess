package json;

public class PieceDto {
    private String position;
    private String pieceName;

    public PieceDto(String position, String pieceName) {
        this.position = position;
        this.pieceName = pieceName;
    }

    public String getPosition() {
        return position;
    }

    public String getPieceName() {
        return pieceName;
    }
}
