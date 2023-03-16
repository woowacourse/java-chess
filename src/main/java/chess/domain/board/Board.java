package chess.domain.board;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.File.A;
import static chess.domain.File.B;
import static chess.domain.File.C;
import static chess.domain.File.D;
import static chess.domain.File.E;
import static chess.domain.File.F;
import static chess.domain.File.G;
import static chess.domain.File.H;
import static chess.domain.Rank.EIGHT;
import static chess.domain.Rank.ONE;
import static chess.domain.Rank.SEVEN;
import static chess.domain.Rank.TWO;

public class Board {

    private static final Map<Color, Rank> INITIAL_PAWN_RANKS_BY_COLOR = Map.of(BLACK, SEVEN,
            WHITE, TWO);
    private static final Map<Color, Rank> INITIAL_NON_PAWNS_RANKS_BY_COLOR = Map.of(BLACK, EIGHT,
            WHITE, ONE);

    private final List<Piece> pieces;

    private Board(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Board createBoardWithStartingPieces() {
        final List<Piece> startingPieces = new ArrayList<>();
        for (Color color : Color.values()) {
            startingPieces.addAll(makeStartingPieces(color));
        }

        return new Board(startingPieces);
    }

    private static List<Piece> makeStartingPieces(final Color color) {
        final List<Piece> startingPieces = new ArrayList<>();
        startingPieces.addAll(makeStartingBishop(color));
        startingPieces.addAll(makeStartingKnight(color));
        startingPieces.addAll(makeStartingRooks(color));
        startingPieces.addAll(makeStatingPawns(color));
        startingPieces.addAll(makeStartingKing(color));
        startingPieces.addAll(makeStartingQueen(color));
        System.out.println();
        return startingPieces;
    }

    private static List<Piece> makeStatingPawns(final Color color) {
        final List<Piece> pawns = new ArrayList<>();
        for (File file : File.values()) {
            pawns.add(new Pawn(file, INITIAL_PAWN_RANKS_BY_COLOR.get(color), color));
        }
        return pawns;
    }

    private static List<Piece> makeStartingRooks(final Color color) {
        return List.of(
                new Rook(A, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color), color),
                new Rook(H, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color), color)
        );
    }

    private static List<Piece> makeStartingKnight(final Color color) {
        return List.of(
                new Knight(B, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color), color),
                new Knight(G, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color), color)
        );
    }

    private static List<Piece> makeStartingBishop(final Color color) {
        return List.of(
                new Bishop(C, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color), color),
                new Bishop(F, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color), color)
        );
    }

    private static List<Piece> makeStartingKing(final Color color) {
        return List.of(
                new King(E, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color), color)
        );
    }

    private static List<Piece> makeStartingQueen(final Color color) {
        return List.of(
                new Queen(D, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color), color)
        );
    }

    public List<Piece> getPieces() {
        return List.copyOf(pieces);
    }
}
