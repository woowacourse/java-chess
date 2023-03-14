package domain.board;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
        final List<Position> rooksPosition = Position.of("A1", "A8", "H1", "H8");

        rooksPosition.forEach(position -> board.put(position, Rook.create(position)));
    }

    private static void addInitialKnights(final HashMap<Position, Piece> board) {
        final List<Position> knightsPosition = Position.of("B1", "B8", "G1", "G8");

        knightsPosition.forEach(position -> board.put(position, Knight.create(position)));
    }

    private static void addInitialBishops(final HashMap<Position, Piece> board) {
        final List<Position> bishopsPosition = Position.of("C1", "C8", "F1", "F8");

        bishopsPosition.forEach(position -> board.put(position, Bishop.create(position)));
    }

    private static void addInitialQueens(final HashMap<Position, Piece> board) {
        final List<Position> queensPosition = Position.of("D1", "D8");

        queensPosition.forEach(position -> board.put(position, Queen.create(position)));
    }

    private static void addInitialPawns(Map<Position, Piece> board) {
        ALL_FILES.chars()
                .mapToObj(file -> String.valueOf((char) file))
                .flatMap(file -> Stream.of(file + WHITE_PAWNS_RANK, file + BLACK_PAWNS_RANK))
                .map(Position::from)
                .forEach(position -> board.put(position, Pawn.create(position)));
    }

    private static void addInitialKings(Map<Position, Piece> board) {
        final List<Position> kingsPosition = Position.of("E1", "E8");

        kingsPosition.forEach(position -> board.put(position, King.create(position)));
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(board);
    }
}
