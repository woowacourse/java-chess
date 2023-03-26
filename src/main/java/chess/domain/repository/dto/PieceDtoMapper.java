package chess.domain.repository.dto;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum PieceDtoMapper {
    PAWN(Pawn.class, "Pawn"),
    BISHOP(Bishop.class, "Bishop"),
    KING(King.class, "King"),
    KNIGHT(Knight.class, "Knight"),
    QUEEN(Queen.class, "Queen"),
    ROOK(Rook.class, "Rook"),
    ;

    private final Class<? extends Piece> piece;
    private final String value;

    PieceDtoMapper(Class<? extends Piece> piece, String value) {
        this.piece = piece;
        this.value = value;
    }

    public static String convertToPieceValue(Piece piece) {
        return Arrays.stream(PieceDtoMapper.values())
                .filter(it -> it.piece.isInstance(piece))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .value;
    }
}
