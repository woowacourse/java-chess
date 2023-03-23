package chess.domain.board.maker;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Rank;
import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.File.*;
import static chess.domain.Rank.*;

public class StartingPiecesFactory implements PiecesFactory {

    private static final Map<Color, Rank> INITIAL_PAWN_RANKS_BY_COLOR = Map.of(BLACK, SEVEN,
            WHITE, TWO);
    private static final Map<Color, Rank> INITIAL_NON_PAWNS_RANKS_BY_COLOR = Map.of(BLACK, EIGHT,
            WHITE, ONE);

    @Override
    public List<Piece> generate() {
        final List<Piece> startingPieces = new ArrayList<>();
        startingPieces.addAll(makeStartingPieces(BLACK));
        startingPieces.addAll(makeStartingPieces(WHITE));
        return startingPieces;
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
}
