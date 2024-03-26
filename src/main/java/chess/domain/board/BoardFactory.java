package chess.domain.board;

import static chess.domain.board.Coordinate.COORDINATE_POOL;

import chess.domain.piece.DummyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.piece.directionmove.Bishop;
import chess.domain.piece.directionmove.Queen;
import chess.domain.piece.directionmove.Rook;
import chess.domain.piece.fixedmove.King;
import chess.domain.piece.fixedmove.Knight;
import chess.domain.piece.pawn.InitialBlackPawn;
import chess.domain.piece.pawn.InitialWhitePawn;
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
        initializeSpecialPiece(INITIAL_WHITE_SPECIAL_RANK, Team.WHITE);
        initializePawn(INITIAL_WHITE_PAWN_RANK, new InitialWhitePawn());
    }

    private static void initializeBlackPiece() {
        initializeSpecialPiece(INITIAL_BLACK_SPECIAL_RANK, Team.BLACK);
        initializePawn(INITIAL_BLACK_PAWN_RANK, new InitialBlackPawn());
    }

    private static void initializePawn(int rankValue, Piece pawn) {
        for (int fileValue = MIN_FILE_RANGE; fileValue <= MAX_FILE_RANGE; fileValue++) {
            INITIAL_BOARD.put(Coordinate.of(File.from(fileValue), Rank.from(rankValue)), pawn);
        }
    }

    private static void initializeSpecialPiece(int rankValue, Team team) {
        INITIAL_BOARD.put(Coordinate.of(File.A, Rank.from(rankValue)), new Rook(team));
        INITIAL_BOARD.put(Coordinate.of(File.B, Rank.from(rankValue)), new Knight(team));
        INITIAL_BOARD.put(Coordinate.of(File.C, Rank.from(rankValue)), new Bishop(team));
        INITIAL_BOARD.put(Coordinate.of(File.D, Rank.from(rankValue)), new Queen(team));
        INITIAL_BOARD.put(Coordinate.of(File.E, Rank.from(rankValue)), new King(team));
        INITIAL_BOARD.put(Coordinate.of(File.F, Rank.from(rankValue)), new Bishop(team));
        INITIAL_BOARD.put(Coordinate.of(File.G, Rank.from(rankValue)), new Knight(team));
        INITIAL_BOARD.put(Coordinate.of(File.H, Rank.from(rankValue)), new Rook(team));
    }
}
