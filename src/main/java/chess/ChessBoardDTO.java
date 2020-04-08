package chess;

import chess.domain.chessPiece.pieceType.PieceColor;

public class ChessBoardDTO {
    private PieceColor pieceColor;
    private String sourcePosition;
    private String targetPosition;

    public ChessBoardDTO(String sourcePosition, String targetPosition, PieceColor pieceColor) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
        this.pieceColor = pieceColor;
    }

    public void setPieceColor(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public String getSourcePosition() {
        return sourcePosition;
    }

    public String getTargetPosition() {
        return targetPosition;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

}
