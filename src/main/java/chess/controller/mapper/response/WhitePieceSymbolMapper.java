package chess.controller.mapper.response;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum WhitePieceSymbolMapper {
    PAWN(Pawn.class, "p"),
    BISHOP(Bishop.class, "b"),
    KING(King.class, "k"),
    KNIGHT(Knight.class, "n"),
    QUEEN(Queen.class, "q"),
    ROOK(Rook.class, "r"),
    ;

    private final Class<? extends Piece> piece;
    private final String viewSymbol;

    WhitePieceSymbolMapper(Class<? extends Piece> piece, String viewSymbol) {
        this.piece = piece;
        this.viewSymbol = viewSymbol;
    }

    public static String getViewSymbolBy(Piece piece) {
        return Arrays.stream(WhitePieceSymbolMapper.values())
                .filter(it -> it.piece.isInstance(piece))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .viewSymbol;
    }
}
