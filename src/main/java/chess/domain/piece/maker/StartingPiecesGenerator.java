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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

public class StartingPiecesGenerator implements PiecesGenerator {

    @Override
    public Set<Piece> generate() {
        final Set<Piece> startingPieces = new HashSet<>();
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
            final Position position = Position.of(file, InitialRankCondition.PAWN.byColor(color));
            pawns.add(new Pawn(position, color));
        }
        return pawns;
    }

    private static List<Piece> makeStartingRooks(final Color color) {
        return List.of(
                new Rook(Position.of(A, InitialRankCondition.NON_PAWN.byColor(color)), color),
                new Rook(Position.of(H, InitialRankCondition.NON_PAWN.byColor(color)), color)
        );
    }

    private static List<Piece> makeStartingKnight(final Color color) {
        return List.of(
                new Knight(Position.of(B, InitialRankCondition.NON_PAWN.byColor(color)), color),
                new Knight(Position.of(G, InitialRankCondition.NON_PAWN.byColor(color)), color)
        );
    }

    private static List<Piece> makeStartingBishop(final Color color) {
        return List.of(
                new Bishop(Position.of(C, InitialRankCondition.NON_PAWN.byColor(color)), color),
                new Bishop(Position.of(F, InitialRankCondition.NON_PAWN.byColor(color)), color)
        );
    }

    private static List<Piece> makeStartingKing(final Color color) {
        return List.of(
                new King(Position.of(E, InitialRankCondition.NON_PAWN.byColor(color)), color)
        );
    }

    private static List<Piece> makeStartingQueen(final Color color) {
        return List.of(
                new Queen(Position.of(D, InitialRankCondition.NON_PAWN.byColor(color)), color)
        );
    }
}
