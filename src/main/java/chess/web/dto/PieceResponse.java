package chess.web.dto;

import chess.domain.piece.Piece;

public class PieceResponse {

    private final String position;
    private final String notation;
    private final String color;

    public PieceResponse(Piece piece, String position) {
        this.position = position;
        this.notation = piece.getNotation();
        this.color = piece.getColor();
    }

    public String getPosition() {
        return position;
    }

    public String getNotation() {
        return notation;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "PieceResponse{" +
                "position='" + position + '\'' +
                ", notation='" + notation + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
