package chess.view;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.function.Predicate;

public enum PieceView {
    R(piece -> piece instanceof Rook, "r"),
    N(piece -> piece instanceof Knight, "n"),
    B(piece -> piece instanceof Bishop, "b"),
    Q(piece -> piece instanceof Queen, "q"),
    K(piece -> piece instanceof King, "k"),
    P(piece -> piece instanceof Pawn, "p"),
    NONE(piece -> piece instanceof EmptyPiece, "."),
    ;

    private final Predicate<Piece> predicate;
    private final String symbol;

    PieceView(final Predicate<Piece> predicate, final String symbol) {
        this.predicate = predicate;
        this.symbol = symbol;
    }

    public static String findValue(final Piece piece) {
        return Arrays.stream(PieceView.values())
                .filter(value -> value.predicate.test(piece))
                .map(value -> {
                    if (piece.isWhite()) {
                        return value.symbol;
                    }
                    return value.symbol.toUpperCase();
                })
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("기물 표식을 찾을 수 없습니다."));
    }

}
