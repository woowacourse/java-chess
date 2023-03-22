package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.HashMap;
import java.util.Map;

public final class BoardFactory {

    private BoardFactory() {
        throw new AssertionError("생성할 수 없는 클래스입니다.");
    }

    public static Board generate() {
        Map<Position, Piece> board = new HashMap<>();
        initializeBoard(board);
        initPawn(board);
        initRook(board);
        initKnight(board);
        initBishop(board);
        initQueen(board);
        initKing(board);
        return Board.from(board);
    }

    private static void initializeBoard(final Map<Position, Piece> board) {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                board.put(Position.of(file, rank), Empty.create());
            }
        }
    }

    private static void initPawn(final Map<Position, Piece> board) {
        for (File file : File.values()) {
            board.put(Position.of(file, Rank.TWO), Pawn.createByColor(Color.WHITE));
            board.put(Position.of(file, Rank.SEVEN), Pawn.createByColor(Color.BLACK));
        }
    }

    private static void initRook(final Map<Position, Piece> board) {
        board.put(Position.of(File.A, Rank.ONE), Rook.create(Color.WHITE));
        board.put(Position.of(File.H, Rank.ONE), Rook.create(Color.WHITE));
        board.put(Position.of(File.A, Rank.EIGHT), Rook.create(Color.BLACK));
        board.put(Position.of(File.H, Rank.EIGHT), Rook.create(Color.BLACK));
    }

    private static void initKnight(final Map<Position, Piece> board) {
        board.put(Position.of(File.B, Rank.ONE), Knight.create(Color.WHITE));
        board.put(Position.of(File.G, Rank.ONE), Knight.create(Color.WHITE));
        board.put(Position.of(File.B, Rank.EIGHT), Knight.create(Color.BLACK));
        board.put(Position.of(File.G, Rank.EIGHT), Knight.create(Color.BLACK));
    }

    private static void initBishop(final Map<Position, Piece> board) {
        board.put(Position.of(File.C, Rank.ONE), Bishop.create(Color.WHITE));
        board.put(Position.of(File.F, Rank.ONE), Bishop.create(Color.WHITE));
        board.put(Position.of(File.C, Rank.EIGHT), Bishop.create(Color.BLACK));
        board.put(Position.of(File.F, Rank.EIGHT), Bishop.create(Color.BLACK));
    }

    private static void initQueen(final Map<Position, Piece> board) {
        board.put(Position.of(File.D, Rank.ONE), Queen.create(Color.WHITE));
        board.put(Position.of(File.D, Rank.EIGHT), Queen.create(Color.BLACK));
    }

    private static void initKing(final Map<Position, Piece> board) {
        board.put(Position.of(File.E, Rank.ONE), King.create(Color.WHITE));
        board.put(Position.of(File.E, Rank.EIGHT), King.create(Color.BLACK));
    }
}
