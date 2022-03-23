package chess.view;

import chess.piece.Bishop;
import chess.piece.Blank;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.Arrays;
import java.util.function.Predicate;

public enum PieceSymbol {

    ROOK("r", piece -> piece instanceof Rook),
    KNIGHT("n", piece -> piece instanceof Knight),
    BISHOP("b", piece -> piece instanceof Bishop),
    KING("k", piece -> piece instanceof King),
    QUEEN("q", piece -> piece instanceof Queen),
    PAWN("p", piece -> piece instanceof Pawn),
    BLANK(".", piece -> piece instanceof Blank);

    private final String symbol;
    private final Predicate<Piece> isMatch;

    PieceSymbol(String symbol, Predicate<Piece> predicate) {
        this.symbol = symbol;
        isMatch = predicate;
    }

    public static String getSymbol(final Piece piece) {
        String symbol = Arrays.stream(PieceSymbol.values())
                .filter(it -> it.isMatch.test(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Piece 입니다."))
                .symbol;
        if (piece.getColor() == Color.BLACK) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}
