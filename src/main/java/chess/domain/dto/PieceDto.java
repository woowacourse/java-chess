package chess.domain.dto;

import chess.domain.piece.Piece;

public class PieceDto {
    private String[] columnString = {"a", "b", "c", "d", "e", "f", "g", "h"};
    private String notation;
    private String position;

    public PieceDto(Piece piece) {
        this.notation = piece.getNotation();
        this.position = "" + columnString[piece.getColumn()] + (8 - piece.getRow());
    }

    public String getNotation() {
        return notation;
    }

    public String getPosition() {
        return position;
    }

}