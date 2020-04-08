package chess.dao;

public class PieceEntity {
    private String position;
    private String team;
    private String pieceType;

    public PieceEntity(String position, String team, String pieceType) {
        this.position = position;
        this.team = team;
        this.pieceType = pieceType;
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return team;
    }

    public String getPieceType() {
        return pieceType;
    }
}
