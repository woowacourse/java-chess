package chess.domain.dto;

import chess.domain.piece.Piece;
public class PieceDto {
    private final String teamColor;
    private final String pieceType;
    private boolean alive;

    public PieceDto(Piece original){
        this.teamColor = original.getColor().name();
        this.pieceType = original.getPieceType();
        this.alive = original.isAlive();
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getTeamColor() {
        return teamColor;
    }

    public String getPieceType() {
        return pieceType;
    }

    public boolean isAlive() {
        return alive;
    }

}
