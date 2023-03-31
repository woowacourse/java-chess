package chess.dto;

import chess.piece.Piece;
import chess.piece.PieceType;

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
        if (piece.getType() == PieceType.KING) {
            return "k";
        }
        if (piece.getType() == PieceType.QUEEN) {
            return "q";
        }
        if (piece.getType() == PieceType.KNIGHT) {
            return "n";
        }
        if (piece.getType() == PieceType.PAWN) {
            return "p";
        }
        if (piece.getType() == PieceType.ROOK) {
            return "r";
        }
        if (piece.getType() == PieceType.BISHOP) {
            return "b";
        }
        return ".";
    }

    public String getView() {
        return view;
    }
}
