package domain.board;

import domain.piece.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static domain.board.File.D;
import static domain.board.File.E;
import static domain.board.File.A;
import static domain.board.File.B;
import static domain.board.File.C;
import static domain.board.File.F;
import static domain.board.File.G;
import static domain.board.File.H;
import static domain.board.Rank.ONE;
import static domain.board.Rank.TWO;
import static domain.board.Rank.SEVEN;
import static domain.board.Rank.EIGHT;
import static domain.piece.PieceColor.BLACK;
import static domain.piece.PieceColor.WHITE;

public class BoardInitializer {
    public static Board initBoard() {
        return new Board(new HashMap<>(initPieces()));
    }

    private static Map<Position, Piece> initPieces() {
        final Map<Position, Piece> piecePositions = new HashMap<>();
        initKing(piecePositions);
        initQueen(piecePositions);
        initBishop(piecePositions);
        initKnight(piecePositions);
        initRook(piecePositions);
        initBlackPawns(piecePositions);
        initWhitePawns(piecePositions);

        return piecePositions;
    }

    private static void initKing(final Map<Position, Piece> piecePositions) {
        piecePositions.put(position(E, ONE), new King(WHITE));
        piecePositions.put(position(E, EIGHT), new King(BLACK));
    }

    private static void initQueen(final Map<Position, Piece> piecePositions) {
        piecePositions.put(position(D, ONE), new Queen(WHITE));
        piecePositions.put(position(D, EIGHT), new Queen(BLACK));
    }

    private static void initBishop(final Map<Position, Piece> piecePositions) {
        piecePositions.put(position(C, ONE), new Bishop(WHITE));
        piecePositions.put(position(F, ONE), new Bishop(WHITE));
        piecePositions.put(position(C, EIGHT), new Bishop(BLACK));
        piecePositions.put(position(F, EIGHT), new Bishop(BLACK));
    }

    private static void initKnight(final Map<Position, Piece> piecePositions) {
        piecePositions.put(position(B, ONE), new Knight(WHITE));
        piecePositions.put(position(G, ONE), new Knight(WHITE));
        piecePositions.put(position(B, EIGHT), new Knight(BLACK));
        piecePositions.put(position(G, EIGHT), new Knight(BLACK));
    }

    private static void initRook(final Map<Position, Piece> piecePositions) {
        piecePositions.put(position(A, ONE), new Rook(WHITE));
        piecePositions.put(position(H, ONE), new Rook(WHITE));
        piecePositions.put(position(A, EIGHT), new Rook(BLACK));
        piecePositions.put(position(H, EIGHT), new Rook(BLACK));

    }

    private static void initWhitePawns(final Map<Position, Piece> piecePositions) {
        Arrays.stream(File.values())
                .forEach(file -> piecePositions.put(position(file, TWO), new Pawn(WHITE)));
    }

    private static void initBlackPawns(final Map<Position, Piece> piecePositions) {
        Arrays.stream(File.values())
                .forEach(file -> piecePositions.put(position(file, SEVEN), new Pawn(BLACK)));
    }

    private static Position position(final File file, final Rank rank) {
        return new Position(file, rank);
    }
}
