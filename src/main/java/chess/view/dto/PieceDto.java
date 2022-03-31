package chess.view.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import java.util.Arrays;

public class PieceDto {

    private final PieceNotation pieceNotation;
    private final PieceColor pieceColor;

    private PieceDto(PieceNotation pieceNotation, PieceColor pieceColor) {
        this.pieceNotation = pieceNotation;
        this.pieceColor = pieceColor;
    }

    public static PieceDto from(Piece piece) {
        PieceType pieceType = piece.getPieceType();
        PieceColor pieceColor = piece.getPieceColor();

        return Arrays.stream(PieceNotation.values())
                .filter(pieceNotation -> pieceNotation.pieceType.equals(pieceType))
                .findFirst()
                .map(pieceNotation -> new PieceDto(pieceNotation, pieceColor))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Piece가 전달됐습니다."));
    }

    public String getConsoleNotation() {
        if (pieceColor.equals(PieceColor.WHITE)) {
            return pieceNotation.whiteName;
        }

        return pieceNotation.blackName;
    }

    @Override
    public String toString() {
        return "PieceDto{" +
                "pieceNotation=" + pieceNotation +
                ", pieceColor=" + pieceColor +
                '}';
    }

    private enum PieceNotation {
        PAWN(PieceType.PAWN, "P", "p"),
        ROOK(PieceType.ROOK, "R", "r"),
        KNIGHT(PieceType.KNIGHT, "N", "n"),
        BISHOP(PieceType.BISHOP, "B", "b"),
        QUEEN(PieceType.QUEEN, "Q", "q"),
        KING(PieceType.KING, "K", "k");

        private final PieceType pieceType;
        private final String blackName;
        private final String whiteName;

        PieceNotation(PieceType pieceType, String blackName, String whiteName) {
            this.pieceType = pieceType;
            this.blackName = blackName;
            this.whiteName = whiteName;
        }
    }
}
