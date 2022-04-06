package chess.dto;

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

    public static PieceDto of(PieceNotation pieceNotation, PieceColor pieceColor) {
        return new PieceDto(pieceNotation, pieceColor);
    }

    public String getConsoleText() {
        if (pieceColor.equals(PieceColor.WHITE)) {
            return pieceNotation.whiteConsoleText;
        }

        return pieceNotation.blackConsoleText;
    }

    public String getImageName() {
        if (pieceColor.equals(PieceColor.WHITE)) {
            return pieceNotation.whiteImageName;
        }

        return pieceNotation.blackImageName;
    }

    public PieceType getPieceType() {
        return pieceNotation.getPieceType();
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public Piece toPiece() {
        return new Piece(pieceNotation.pieceType, pieceColor);
    }

    @Override
    public String toString() {
        return "PieceDto{" +
                "pieceNotation=" + pieceNotation +
                ", pieceColor=" + pieceColor +
                '}';
    }

    // TODO: DTO가 가지고 있을 정보가 맞을까?
    private enum PieceNotation {
        PAWN(PieceType.PAWN, "P", "p", "pawn_black", "pawn_white"),
        ROOK(PieceType.ROOK, "R", "r", "rook_black", "rook_white"),
        KNIGHT(PieceType.KNIGHT, "N", "n", "knight_black", "knight_white"),
        BISHOP(PieceType.BISHOP, "B", "b", "bishop_black", "bishop_white"),
        QUEEN(PieceType.QUEEN, "Q", "q", "queen_black", "queen_white"),
        KING(PieceType.KING, "K", "k", "king_black", "king_white");

        private final PieceType pieceType;
        private final String blackConsoleText;
        private final String whiteConsoleText;
        private final String blackImageName;
        private final String whiteImageName;

        PieceNotation(PieceType pieceType, String blackConsoleText, String whiteConsoleText,
                      String blackImageName, String whiteImageName) {
            this.pieceType = pieceType;
            this.blackConsoleText = blackConsoleText;
            this.whiteConsoleText = whiteConsoleText;
            this.blackImageName = blackImageName;
            this.whiteImageName = whiteImageName;
        }

        public PieceType getPieceType() {
            return pieceType;
        }
    }
}
