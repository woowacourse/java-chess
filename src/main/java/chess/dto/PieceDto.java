package chess.dto;

import chess.domain.piece.Piece;

public class PieceDto {
    private String teamColor;
    private String pieceType;
    private String alive;

    public PieceDto(String teamColor, String pieceType, String alive) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
        this.alive = alive;
    }

    public PieceDto(Piece original) {
        this.teamColor = original.getColor().name();
        this.pieceType = original.getPieceType();
        this.alive = String.valueOf(original.isAlive());
    }

    public String getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }

    public String getPieceType() {
        return pieceType;
    }

    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }

    public String getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = String.valueOf(alive);
    }

}
