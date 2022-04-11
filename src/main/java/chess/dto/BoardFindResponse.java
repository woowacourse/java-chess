package chess.dto;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class BoardFindResponse {

    private final String colorResponse;
    private final String pieceResponse;

    public BoardFindResponse(String color, String piece) {
        this.colorResponse = color;
        this.pieceResponse = piece;
    }

    public String getColorResponse() {
        return colorResponse;
    }

    public String getPieceResponse() {
        return pieceResponse;
    }

    public Color getColor() {
        return Color.nameOf(colorResponse);
    }

    public Piece getPiece() {
        return PieceType.createPiece(colorResponse, pieceResponse);
    }
}
