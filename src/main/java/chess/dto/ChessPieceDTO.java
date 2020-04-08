package chess.dto;

public class ChessPieceDTO {
    String pieceName;
    String team;
    int x;
    int y;

    public String getPieceName() {
        return pieceName;
    }

    public String getTeam() {
        return team;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
