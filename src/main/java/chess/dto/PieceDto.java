package chess.dto;

import chess.piece.Piece;

public class PieceDto {

    private final String view;

    public PieceDto(final Piece piece) {
        this.view = parseByTeam(piece);
    }

    private String parseByTeam(final Piece piece) {
        if (piece.isBlack()) {
            return parseToPiece(piece).toUpperCase();
        }

        return parseToPiece(piece);
    }

    private String parseToPiece(final Piece piece) {
        if (piece.isKing()) {
            return "k";
        }
        if (piece.isQueen()) {
            return "q";
        }
        if (piece.isKnight()) {
            return "n";
        }
        if (piece.isPawn()) {
            return "p";
        }
        if (piece.isRook()) {
            return "r";
        }
        if (piece.isBishop()) {
            return "b";
        }
        return ".";
    }

    public String getView() {
        return view;
    }
}
