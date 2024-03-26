package chess.domain.board;

import static chess.domain.board.Coordinate.COORDINATE_POOL;
import static chess.domain.piece.directionmove.Bishop.BLACK_BISHOP;
import static chess.domain.piece.directionmove.Bishop.WHITE_BISHOP;
import static chess.domain.piece.directionmove.Queen.BLACK_QUEEN;
import static chess.domain.piece.directionmove.Queen.WHITE_QUEEN;
import static chess.domain.piece.directionmove.Rook.BLACK_ROOK;
import static chess.domain.piece.directionmove.Rook.WHITE_ROOK;
import static chess.domain.piece.fixedmove.King.BLACK_KING;
import static chess.domain.piece.fixedmove.King.WHITE_KING;
import static chess.domain.piece.fixedmove.Knight.BLACK_KNIGHT;
import static chess.domain.piece.fixedmove.Knight.WHITE_KNIGHT;
import static chess.domain.piece.pawn.InitialBlackPawn.INITIAL_BLACK_PAWN;
import static chess.domain.piece.pawn.InitialWhitePawn.INITIAL_WHITE_PAWN;

import chess.domain.piece.DummyPiece;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    private static final int INITIAL_WHITE_SPECIAL_RANK = 1;
    private static final int INITIAL_WHITE_PAWN_RANK = 2;
    private static final int INITIAL_BLACK_SPECIAL_RANK = 8;
    private static final int INITIAL_BLACK_PAWN_RANK = 7;
    private static final char MIN_FILE_RANGE = 'a';
    private static final char MAX_FILE_RANGE = 'h';
    private static final Map<Coordinate, Piece> INITIAL_BOARD = new HashMap<>();

    private BoardFactory() {
    }

    public static Map<Coordinate, Piece> createInitialPieces() {
        INITIAL_BOARD.putAll(initialEmpty());
        initializeWhitePiece();
        initializeBlackPiece();
        return INITIAL_BOARD;
    }

    static Map<Coordinate, Piece> initialEmpty() {
        Map<Coordinate, Piece> emptyBoard = new HashMap<>();
        COORDINATE_POOL.values().forEach(coordinate -> emptyBoard.put(coordinate, DummyPiece.getInstance()));
        return emptyBoard;
    }

    private static void initializeWhitePiece() {
        INITIAL_BOARD.put(Coordinate.of(File.A, Rank.from(INITIAL_WHITE_SPECIAL_RANK)), WHITE_ROOK);
        INITIAL_BOARD.put(Coordinate.of(File.B, Rank.from(INITIAL_WHITE_SPECIAL_RANK)), WHITE_KNIGHT);
        INITIAL_BOARD.put(Coordinate.of(File.C, Rank.from(INITIAL_WHITE_SPECIAL_RANK)), WHITE_BISHOP);
        INITIAL_BOARD.put(Coordinate.of(File.D, Rank.from(INITIAL_WHITE_SPECIAL_RANK)), WHITE_QUEEN);
        INITIAL_BOARD.put(Coordinate.of(File.E, Rank.from(INITIAL_WHITE_SPECIAL_RANK)), WHITE_KING);
        INITIAL_BOARD.put(Coordinate.of(File.F, Rank.from(INITIAL_WHITE_SPECIAL_RANK)), WHITE_BISHOP);
        INITIAL_BOARD.put(Coordinate.of(File.G, Rank.from(INITIAL_WHITE_SPECIAL_RANK)), WHITE_KNIGHT);
        INITIAL_BOARD.put(Coordinate.of(File.H, Rank.from(INITIAL_WHITE_SPECIAL_RANK)), WHITE_ROOK);
        initializePawn(INITIAL_WHITE_PAWN_RANK, INITIAL_WHITE_PAWN);
    }

    private static void initializeBlackPiece() {
        INITIAL_BOARD.put(Coordinate.of(File.A, Rank.from(INITIAL_BLACK_SPECIAL_RANK)), BLACK_ROOK);
        INITIAL_BOARD.put(Coordinate.of(File.B, Rank.from(INITIAL_BLACK_SPECIAL_RANK)), BLACK_KNIGHT);
        INITIAL_BOARD.put(Coordinate.of(File.C, Rank.from(INITIAL_BLACK_SPECIAL_RANK)), BLACK_BISHOP);
        INITIAL_BOARD.put(Coordinate.of(File.D, Rank.from(INITIAL_BLACK_SPECIAL_RANK)), BLACK_QUEEN);
        INITIAL_BOARD.put(Coordinate.of(File.E, Rank.from(INITIAL_BLACK_SPECIAL_RANK)), BLACK_KING);
        INITIAL_BOARD.put(Coordinate.of(File.F, Rank.from(INITIAL_BLACK_SPECIAL_RANK)), BLACK_BISHOP);
        INITIAL_BOARD.put(Coordinate.of(File.G, Rank.from(INITIAL_BLACK_SPECIAL_RANK)), BLACK_KNIGHT);
        INITIAL_BOARD.put(Coordinate.of(File.H, Rank.from(INITIAL_BLACK_SPECIAL_RANK)), BLACK_ROOK);
        initializePawn(INITIAL_BLACK_PAWN_RANK, INITIAL_BLACK_PAWN);
    }

    private static void initializePawn(int rankValue, Piece pawn) {
        for (int fileValue = MIN_FILE_RANGE; fileValue <= MAX_FILE_RANGE; fileValue++) {
            INITIAL_BOARD.put(Coordinate.of(File.from(fileValue), Rank.from(rankValue)), pawn);
        }
    }
}
