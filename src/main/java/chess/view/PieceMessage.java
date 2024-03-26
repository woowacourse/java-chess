package chess.view;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum PieceMessage {
    BLACK_PAWN(BlackPawn.class, true, "P"),
    BLACK_KNIGHT(Knight.class, true, "N"),
    BLACK_BISHOP(Bishop.class, true, "B"),
    BLACK_ROOK(Rook.class, true, "R"),
    BLACK_QUEEN(Queen.class, true, "Q"),
    BLACK_KING(King.class, true, "K"),

    WHITE_PAWN(WhitePawn.class, false, "p"),
    WHITE_KNIGHT(Knight.class, false, "n"),
    WHITE_BISHOP(Bishop.class, false, "b"),
    WHITE_ROOK(Rook.class, false, "r"),
    WHITE_QUEEN(Queen.class, false, "q"),
    WHITE_KING(King.class, false, "k");

    private final Class<? extends Piece> pieceType;
    private final boolean isBlackTeam;
    private final String message;

    PieceMessage(Class<? extends Piece> pieceType, boolean isBlackTeam, String message) {
        this.pieceType = pieceType;
        this.isBlackTeam = isBlackTeam;
        this.message = message;
    }

    public static String messageOf(Piece targetPiece) {
        return Arrays.stream(values())
                .filter(pieceMessage -> pieceMessage.isBlackTeam == targetPiece.isBlackTeam())
                .filter(pieceMessage -> pieceMessage.pieceType == targetPiece.getClass())
                .findAny()
                .map(pieceMessage -> pieceMessage.message)
                .orElseThrow(() -> new NoSuchElementException("기물에 해당하는 메시지가 없습니다."));
    }
}
