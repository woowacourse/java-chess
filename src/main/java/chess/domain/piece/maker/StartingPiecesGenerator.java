package chess.domain.piece.maker;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static chess.domain.piece.property.Color.BLACK;
import static chess.domain.piece.property.Color.WHITE;
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

public class StartingPiecesGenerator implements PiecesGenerator {

    private static final Map<Color, Rank> INITIAL_PAWN_RANKS_BY_COLOR = Map.of(
            BLACK, SEVEN,
            WHITE, TWO
    );
    private static final Map<Color, Rank> INITIAL_NON_PAWNS_RANKS_BY_COLOR = Map.of(
            BLACK, EIGHT,
            WHITE, ONE
    );

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
            final Position position = new Position(file, INITIAL_PAWN_RANKS_BY_COLOR.get(color));
            pawns.add(new Pawn(position, color));
        }
        return pawns;
    }

    private static List<Piece> makeStartingRooks(final Color color) {
        return List.of(
                new Rook(new Position(A, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color)), color),
                new Rook(new Position(H, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color)), color)
        );
    }

    private static List<Piece> makeStartingKnight(final Color color) {
        return List.of(
                new Knight(new Position(B, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color)), color),
                new Knight(new Position(G, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color)), color)
        );
    }

    private static List<Piece> makeStartingBishop(final Color color) {
        return List.of(
                new Bishop(new Position(C, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color)), color),
                new Bishop(new Position(F, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color)), color)
        );
    }

    private static List<Piece> makeStartingKing(final Color color) {
        return List.of(
                new King(new Position(E, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color)), color)
        );
    }

    private static List<Piece> makeStartingQueen(final Color color) {
        return List.of(
                new Queen(new Position(D, INITIAL_NON_PAWNS_RANKS_BY_COLOR.get(color)), color)
        );
    }
}
