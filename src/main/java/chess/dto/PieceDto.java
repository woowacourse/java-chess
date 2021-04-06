package chess.dto;

import chess.domain.piece.Piece;

public class PieceDto {
    private String teamColor;
    private String pieceType;
    private boolean alive;

    public PieceDto(Piece original) {
        this.teamColor = original.getColor().name();
        this.pieceType = original.getPieceType();
        this.alive = original.isAlive();
    }

    public String getTeamColor() {
        return teamColor;
    }

    public String getPieceType() {
        return pieceType;
    }

    public boolean getAlive() {
        return alive;
    }

}
