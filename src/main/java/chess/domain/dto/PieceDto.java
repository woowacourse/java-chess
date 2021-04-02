package chess.domain.dto;

import chess.domain.piece.Piece;
public class PieceDto {
    private String teamColor;
    private String pieceType;
    private String alive;

    public PieceDto(){
    }
    public PieceDto(Piece original){
        this.teamColor = original.getColor().name();
        this.pieceType = original.getPieceType();
        this.alive = String.valueOf(original.isAlive());
    }

    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }

    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }

    public void setAlive(boolean alive) {
        this.alive = String.valueOf(alive);
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
