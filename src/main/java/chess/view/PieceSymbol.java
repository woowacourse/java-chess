package chess.view;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.Team;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
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
        this.isMatch = predicate;
    }

    public static String getSymbol(final Piece piece) {
        String symbol = Arrays.stream(PieceSymbol.values())
                .filter(it -> it.isMatch.test(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Piece 입니다."))
                .symbol;
        if (piece.getTeam() == Team.BLACK) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}
