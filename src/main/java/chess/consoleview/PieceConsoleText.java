package chess.consoleview;

import chess.domain.piece.PieceColor;
import chess.dto.response.PieceDto;

public enum PieceConsoleText {
    PAWN("P", "p"),
    ROOK("R", "r"),
    KNIGHT("N", "n"),
    BISHOP("B", "b"),
    QUEEN("Q", "q"),
    KING("K", "k"),
    EMPTY(".", ".");

    private final String black;
    private final String white;

    PieceConsoleText(String black, String white) {
        this.black = black;
        this.white = white;
    }

    public static PieceConsoleText from(PieceDto pieceDto) {
        return valueOf(pieceDto.getPieceType().name());
    }

    public static String emptyText() {
        return EMPTY.black;
    }

    public String getText(PieceColor pieceColor) {
        if (pieceColor.equals(PieceColor.WHITE)) {
            return white;
        }

        return black;
    }
}
