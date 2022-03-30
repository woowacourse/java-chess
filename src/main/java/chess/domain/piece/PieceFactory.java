package chess.domain.piece;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static chess.domain.position.File.A;
import static chess.domain.position.File.B;
import static chess.domain.position.File.C;
import static chess.domain.position.File.D;
import static chess.domain.position.File.E;
import static chess.domain.position.File.F;
import static chess.domain.position.File.G;
import static chess.domain.position.File.H;
import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.TWO;
import static java.util.stream.Collectors.toMap;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private PieceFactory() {
    }

    public static Map<Position, Piece> createPieces() {
        Map<Position, Piece> pieces = new HashMap<>();

        pieces.putAll(whitePieces());
        pieces.putAll(blackPieces());

        return pieces;
    }

    private static Map<Position, Piece> whitePieces() {
        Map<Position, Piece> pieces = createPawn(TWO, WHITE, "p");

        Map<String, String> symbols = Map.of(
                "rook", "r",
                "knight", "n",
                "bishop", "b",
                "queen", "q",
                "king", "k"
        );

        createPieces(pieces, ONE, WHITE, symbols);

        return pieces;
    }

    private static Map<Position, Piece> blackPieces() {
        Map<Position, Piece> pieces = createPawn(SEVEN, BLACK, "P");

        Map<String, String> symbols = Map.of(
                "rook", "R",
                "knight", "N",
                "bishop", "B",
                "queen", "Q",
                "king", "K"
        );

        createPieces(pieces, EIGHT, BLACK, symbols);

        return pieces;
    }

    private static Map<Position, Piece> createPawn(Rank two, Team white, String p) {
        Map<Position, Piece> pieces = Arrays.stream(File.values())
                .collect(toMap(
                        file -> Position.of(file, two), file -> new Pawn(white, p)
                ));
        return pieces;
    }

    private static void createPieces(Map<Position, Piece> pieces, Rank rank, Team team,
                                     Map<String, String> symbols) {
        pieces.put(Position.of(A, rank), new Rook(team, symbols.get("rook")));
        pieces.put(Position.of(B, rank), new Knight(team, symbols.get("knight")));
        pieces.put(Position.of(C, rank), new Bishop(team, symbols.get("bishop")));
        pieces.put(Position.of(D, rank), new Queen(team, symbols.get("queen")));
        pieces.put(Position.of(E, rank), new King(team, symbols.get("king")));
        pieces.put(Position.of(F, rank), new Bishop(team, symbols.get("bishop")));
        pieces.put(Position.of(G, rank), new Knight(team, symbols.get("knight")));
        pieces.put(Position.of(H, rank), new Rook(team, symbols.get("rook")));
    }
}
