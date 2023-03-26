package chess.view;

import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum PieceMessage {
    KING(PieceType.KING, "K"),
    QUEEN(PieceType.QUEEN, "Q"),
    BISHOP(PieceType.BISHOP, "B"),
    KNIGHT(PieceType.KNIGHT, "N"),
    ROOK(PieceType.ROOK, "R"),
    PAWN(PieceType.PAWN, "P"),
    EMPTY(PieceType.EMPTY, ".");

    private static final Map<PieceType, String> MESSAGES;

    private final PieceType pieceType;
    private final String message;

    PieceMessage(PieceType pieceType, String message) {
        this.pieceType = pieceType;
        this.message = message;
    }

    static {
        MESSAGES = new EnumMap<>(PieceType.class);
        for (PieceType pieceType : PieceType.values()) {
            MESSAGES.put(pieceType, findMessage(pieceType));
        }
    }

    private static String findMessage(PieceType pieceType) {
        return Arrays.stream(PieceMessage.values()).filter(pieceMessage -> pieceMessage.pieceType == pieceType)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .message;
    }

    public static String getMessage(PieceType pieceType) {
        return MESSAGES.get(pieceType);
    }
}
