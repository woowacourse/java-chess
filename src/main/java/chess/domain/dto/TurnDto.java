package chess.domain.dto;

import chess.domain.piece.PieceColor;

public class TurnDto {

    private PieceColor pieceColor;

    public TurnDto(String color) {
        this.pieceColor = PieceColor.translateTurnColor(color);
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }
}
