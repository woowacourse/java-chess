package domain.board;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.Team;
import domain.position.Position;
import domain.position.Positions;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Board {
    private static final String ALL_FILES = "ABCDEFGH";
    private static final int WHITE_PAWNS_RANK = 2;
    private static final int BLACK_PAWNS_RANK = 7;

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        final HashMap<Position, Piece> board = new HashMap<>();

        addInitialPawns(board);
        addInitialKings(board);
        addInitialQueens(board);
        addInitialBishops(board);
        addInitialKnights(board);
        addInitialRooks(board);

        return new Board(board);
    }

    private static void addInitialRooks(final HashMap<Position, Piece> board) {
        final List<Position> black = Positions.of("A8", "H8");
        final List<Position> white = Positions.of("A1", "H1");

        black.forEach(position -> board.put(position, new Rook(Team.BLACK)));
        white.forEach(position -> board.put(position, new Rook(Team.WHITE)));
    }

    private static void addInitialKnights(final HashMap<Position, Piece> board) {
        final List<Position> black = Positions.of("B8", "G8");
        final List<Position> white = Positions.of("B1", "G1");

        black.forEach(position -> board.put(position, new Knight(Team.BLACK)));
        white.forEach(position -> board.put(position, new Knight(Team.WHITE)));
    }

    private static void addInitialBishops(final HashMap<Position, Piece> board) {
        final List<Position> black = Positions.of("C8", "F8");
        final List<Position> white = Positions.of("C1", "F1");

        black.forEach(position -> board.put(position, new Bishop(Team.BLACK)));
        white.forEach(position -> board.put(position, new Bishop(Team.WHITE)));
    }

    private static void addInitialQueens(final HashMap<Position, Piece> board) {
        board.put(Positions.from("D8"), new Queen(Team.BLACK));
        board.put(Positions.from("D1"), new Queen(Team.WHITE));
    }

    private static void addInitialPawns(Map<Position, Piece> board) {
        final List<Position> black = Positions.of("A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7");
        final List<Position> white = Positions.of("A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2");

        black.forEach(position -> board.put(position, new Pawn(Team.BLACK)));
        white.forEach(position -> board.put(position, new Pawn(Team.WHITE)));
    }

    private static void addInitialKings(Map<Position, Piece> board) {
        board.put(Positions.from("E8"), new King(Team.BLACK));
        board.put(Positions.from("E1"), new King(Team.WHITE));
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(board);
    }
}
