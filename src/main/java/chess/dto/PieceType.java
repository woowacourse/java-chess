package chess.dto;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;

public enum PieceType {

    KING(King.class),
    QUEEN(Queen.class),
    ROOK(Rook.class),
    BISHOP(Bishop.class),
    KNIGHT(Knight.class),
    PAWN(Pawn.class);

    private final Class<? extends Piece> category;

    PieceType(Class<? extends Piece> category) {
        this.category = category;
    }

    public static PieceType from(Piece piece) {
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType.category == piece.getClass())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 기물이 존재하지 않습니다."));
    }
}
