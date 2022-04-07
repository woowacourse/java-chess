package chess.dto.response;

import chess.domain.game.ChessGame;
import chess.domain.piece.PieceColor;

public class PieceColorDto {
    private final PieceColor pieceColor;

    private PieceColorDto(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public static PieceColorDto from(ChessGame chessGame) {
        if (chessGame.isWhiteTurn()) {
            return new PieceColorDto(PieceColor.WHITE);
        }

        return new PieceColorDto(PieceColor.BLACK);
    }

    public static PieceColorDto from(String pieceColorName) {
        return new PieceColorDto(PieceColor.valueOf(pieceColorName));
    }

    public static PieceColorDto from(PieceColor pieceColor) {
        return new PieceColorDto(pieceColor);
    }

    public boolean isWhiteTurn() {
        return pieceColor.equals(PieceColor.WHITE);
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }
}

