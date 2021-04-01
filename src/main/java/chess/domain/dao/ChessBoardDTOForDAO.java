package chess.domain.dao;

public class ChessBoardDTOForDAO {
    private String position;
    private String teamColor;
    private String pieceType;
    private String alive;

    public ChessBoardDTOForDAO(String position, String teamColor, String pieceType, String alive) {
        this.position = position;
        this.teamColor = teamColor;
        this.pieceType = pieceType;
        this.alive = alive;
    }


    public void setPosition(String position) {
        this.position = position;
    }

    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }

    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }
    public String getPosition() {
        return position;
    }

    public String getTeamColor() {
        return teamColor;
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getAlive() {
        return alive;
    }
}
