package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Nothing;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.Arrays;

public enum Symbol {

    NOTHING(".", "BLANK", new Nothing()),
    PAWN_WHITE("p", "PAWN_WHITE", new Pawn(Team.WHITE, "p")),
    BISHOP_WHITE("b", "BISHOP_WHITE", new Bishop(Team.WHITE, "b")),
    KNIGHT_WHITE("n", "KNIGHT_WHITE", new Knight(Team.WHITE, "n")),
    ROOK_WHITE("r", "ROOK_WHITE", new Rook(Team.WHITE, "r")),
    QUEEN_WHITE("q", "QUEEN_WHITE", new Queen(Team.WHITE, "q")),
    KING_WHITE("k", "KING_WHITE", new King(Team.WHITE, "k")),

    PAWN_BLACK("P", "PAWN_BLACK", new Pawn(Team.BLACK, "P")),
    BISHOP_BLACK("B", "BISHOP_BLACK", new Bishop(Team.BLACK, "B")),
    KNIGHT_BLACK("N", "KNIGHT_BLACK", new Knight(Team.BLACK, "N")),
    ROOK_BLACK("R", "ROOK_BLACK", new Rook(Team.BLACK, "R")),
    QUEEN_BLACK("Q", "QUEEN_BLACK", new Queen(Team.BLACK, "Q")),
    KING_BLACK("K", "KING_BLACK", new King(Team.BLACK, "K"));

    private final String consoleSymbol;
    private final String webSymbol;
    private final Piece piece;

    Symbol(String consoleSymbol, String webSymbol, Piece piece) {
        this.consoleSymbol = consoleSymbol;
        this.webSymbol = webSymbol;
        this.piece = piece;
    }

    public static String consoleSymbolToWebSymbol(final String consoleSymbol) {
        return Arrays.stream(Symbol.values())
                .filter(symbol -> symbol.consoleSymbol.equals(consoleSymbol))
                .findFirst()
                .map(symbol -> symbol.webSymbol)
                .orElse(NOTHING.webSymbol);
    }

    public static Piece webSymbolToPiece(final String webSymbol) {
        return Arrays.stream(Symbol.values())
                .filter(symbol -> symbol.webSymbol.equals(webSymbol))
                .findFirst()
                .map(symbol -> symbol.piece)
                .orElse(NOTHING.piece);
    }
}
