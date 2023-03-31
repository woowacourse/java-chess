package chess.controller;

import chess.chessboard.Side;
import chess.piece.*;

public class PieceDto {
    private final String outputFormat;

    private PieceDto(final String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public static PieceDto from(final Piece piece) {
        String outputFormat = ".";

        if (piece instanceof Pawn) {
            outputFormat = "p";
        }
        if (piece instanceof Knight) {
            outputFormat = "n";
        }
        if (piece instanceof Bishop) {
            outputFormat = "b";
        }
        if (piece instanceof Rook) {
            outputFormat = "r";
        }
        if (piece instanceof Queen) {
            outputFormat = "q";
        }
        if (piece instanceof King) {
            outputFormat = "k";
        }

        if (piece.isSameSide(Side.BLACK)) {
            outputFormat = outputFormat.toUpperCase();
        }

        return new PieceDto(outputFormat);
    }

    public String getOutputFormat() {
        return outputFormat;
    }
}
