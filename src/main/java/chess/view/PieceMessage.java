package chess.view;

import chess.domain.piece.Role;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum PieceMessage {
    KING(Role.KING, "K"),
    QUEEN(Role.QUEEN, "Q"),
    BISHOP(Role.BISHOP, "B"),
    KNIGHT(Role.KNIGHT, "N"),
    ROOK(Role.ROOK, "R"),
    PAWN(Role.PAWN, "P"),
    EMPTY(Role.EMPTY, ".");

    private static final Map<Role, String> MESSAGES;

    private final Role role;
    private final String message;

    PieceMessage(Role role, String message) {
        this.role = role;
        this.message = message;
    }

    static {
        MESSAGES = new EnumMap<>(Role.class);
        for (Role role : Role.values()) {
            MESSAGES.put(role, findMessage(role));
        }
    }

    private static String findMessage(Role role) {
        return Arrays.stream(PieceMessage.values()).filter(pieceMessage -> pieceMessage.role == role)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .message;
    }

    public static String getMessage(Role role) {
        return MESSAGES.get(role);
    }
}
