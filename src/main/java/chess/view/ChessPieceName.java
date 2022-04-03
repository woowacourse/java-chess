package chess.view;

import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.function.Predicate;

public enum ChessPieceName {
    PAWN(Pawn.class::isInstance, "p"),
    KNIGHT(Knight.class::isInstance, "n"),
    BISHOP(Bishop.class::isInstance, "b"),
    ROOK(Rook.class::isInstance, "r"),
    QUEEN(Queen.class::isInstance, "q"),
    KING(King.class::isInstance, "k");

    private final Predicate<ChessPiece> condition;
    private final String name;

    ChessPieceName(Predicate<ChessPiece> condition, String name) {
        this.condition = condition;
        this.name = name;
    }

    public static String of(ChessPiece chessPiece) {
        return Arrays.stream(ChessPieceName.values())
                .filter(it -> it.condition.test(chessPiece))
                .map(it -> it.name)
                .findFirst()
                .orElseThrow();
    }
}
