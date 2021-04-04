package chess.domain.dto;

import chess.domain.piece.PieceColor;

public class TurnDTO {

    private PieceColor pieceColor;

    public TurnDTO(String color) {
        this.pieceColor = PieceColor.translateTurnColor(color);
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }
}
