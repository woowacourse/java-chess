package chess.domain.dao;

import java.util.Objects;

public class ChessBoardVo {
    private String position;
    private String teamColor;
    private String pieceType;
    private String alive;

    public ChessBoardVo(String position, String teamColor, String pieceType, String alive) {
        this.position = position;
        this.teamColor = teamColor;
        this.pieceType = pieceType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoardVo that = (ChessBoardVo) o;
        return Objects.equals(position, that.position) && Objects.equals(teamColor, that.teamColor) && Objects.equals(pieceType, that.pieceType) && Objects.equals(alive, that.alive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, teamColor, pieceType, alive);
    }
}
