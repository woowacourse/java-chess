package techcourse.fp.chess.domain;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

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

        return new Board(board);
    }

    private static void initializeBoard(final Map<Position, Piece> board) {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                board.put(Position.of(file, rank), new Empty());
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
        board.put(Position.of(File.A, Rank.ONE), new Rook(Color.WHITE));
        board.put(Position.of(File.H, Rank.ONE), new Rook(Color.WHITE));
        board.put(Position.of(File.A, Rank.EIGHT), new Rook(Color.BLACK));
        board.put(Position.of(File.H, Rank.EIGHT), new Rook(Color.BLACK));
    }

    private static void initKnight(final Map<Position, Piece> board) {
        board.put(Position.of(File.B, Rank.ONE), new Knight(Color.WHITE));
        board.put(Position.of(File.G, Rank.ONE), new Knight(Color.WHITE));
        board.put(Position.of(File.B, Rank.EIGHT), new Knight(Color.BLACK));
        board.put(Position.of(File.G, Rank.EIGHT), new Knight(Color.BLACK));
    }

    private static void initBishop(final Map<Position, Piece> board) {
        board.put(Position.of(File.C, Rank.ONE), new Bishop(Color.WHITE));
        board.put(Position.of(File.F, Rank.ONE), new Bishop(Color.WHITE));
        board.put(Position.of(File.C, Rank.EIGHT), new Bishop(Color.BLACK));
        board.put(Position.of(File.F, Rank.EIGHT), new Bishop(Color.BLACK));
    }

    private static void initQueen(final Map<Position, Piece> board) {
        board.put(Position.of(File.D, Rank.ONE), new Queen(Color.WHITE));
        board.put(Position.of(File.D, Rank.EIGHT), new Queen(Color.BLACK));
    }

    private static void initKing(final Map<Position, Piece> board) {
        board.put(Position.of(File.E, Rank.ONE), new King(Color.WHITE));
        board.put(Position.of(File.E, Rank.EIGHT), new King(Color.BLACK));
    }
}
