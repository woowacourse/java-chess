package chess.dao.initialboard;

import static chess.dao.initialboard.PieceIdFactory.of;
import static java.util.Map.entry;

import java.util.Map;

public class InitialBoard {

    private static final Map<String, Integer> pieces;

    static {
        pieces = Map.ofEntries(
                entry("a1", of("whiteRook")), entry("b1", of("whiteKnight")),
                entry("c1", of("whiteBishop")), entry("d1", of("whiteQueen")),
                entry("e1", of("whiteKing")), entry("f1", of("whiteBishop")),
                entry("g1", of("whiteKnight")), entry("h1", of("whiteRook")),
                entry("a2", of("whitePawn")), entry("b2", of("whitePawn")),
                entry("c2", of("whitePawn")), entry("d2", of("whitePawn")),
                entry("e2", of("whitePawn")), entry("f2", of("whitePawn")),
                entry("g2", of("whitePawn")), entry("h2", of("whitePawn")),

                entry("a8", of("blackRook")), entry("b8", of("blackKnight")),
                entry("c8", of("blackBishop")), entry("d8", of("blackQueen")),
                entry("e8", of("blackKing")), entry("f8", of("blackBishop")),
                entry("g8", of("blackKnight")), entry("h8", of("blackRook")),
                entry("a7", of("blackPawn")), entry("b7", of("blackPawn")),
                entry("c7", of("blackPawn")), entry("d7", of("blackPawn")),
                entry("e7", of("blackPawn")), entry("f7", of("blackPawn")),
                entry("g7", of("blackPawn")), entry("h7", of("blackPawn"))
        );
    }

    public static Map<String, Integer> getInitialPiecesIdAndLocation() {
        return pieces;
    }

    private InitialBoard() {
    }
}
