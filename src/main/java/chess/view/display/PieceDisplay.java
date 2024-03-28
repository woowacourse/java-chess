package chess.view.display;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum PieceDisplay {
    WHITE_FIRST_PAWN(PieceType.WHITE_FIRST_PAWN, "p"),
    WHITE_PAWN(PieceType.WHITE_PAWN, "p"),
    WHITE_KNIGHT(PieceType.WHITE_KNIGHT, "n"),
    WHITE_BISHOP(PieceType.WHITE_BISHOP, "b"),
    WHITE_ROOK(PieceType.WHITE_ROOK, "r"),
    WHITE_QUEEN(PieceType.WHITE_QUEEN, "q"),
    WHITE_KING(PieceType.WHITE_KING, "k"),
    BLACK_FIRST_PAWN(PieceType.BLACK_FIRST_PAWN, "p"),
    BLACK_PAWN(PieceType.BLACK_PAWN, "P"),
    BLACK_KNIGHT(PieceType.BLACK_KNIGHT, "N"),
    BLACK_BISHOP(PieceType.BLACK_BISHOP, "B"),
    BLACK_ROOK(PieceType.BLACK_ROOK, "R"),
    BLACK_QUEEN(PieceType.BLACK_QUEEN, "Q"),
    BLACK_KING(PieceType.BLACK_KING, "K"),
    NONE(PieceType.BLANK, ".");

    private final PieceType pieceType;
    private final String display;

    PieceDisplay(PieceType pieceType, String display) {
        this.pieceType = pieceType;
        this.display = display;
    }

    public static List<String> makeBoardDisplay(Map<Position, PieceType> board) {
        List<String> result = new ArrayList<>();
        for (int y = 8; y >= 1; y--) {
            result.add(makeBoardLine(board, y));
        }
        return result;
    }

    private static String makeBoardLine(Map<Position, PieceType> board, int y) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int x = 1; x <= 8; x++) {
            stringBuilder.append(findPieceDisplay(board.get(new Position(x, y))));
        }
        return stringBuilder.toString();
    }

    private static String findPieceDisplay(PieceType pieceType) {
        return Arrays.stream(PieceDisplay.values())
                .filter(piece -> piece.pieceType == pieceType)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("올바르지 않은 PieceType입니다."))
                .display;
    }
}
