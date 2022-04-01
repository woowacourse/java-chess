package chess.domain;

import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private static final int INITIAL_CAPACITY = 64;

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        final Map<Position, Piece> board = new LinkedHashMap<>(INITIAL_CAPACITY);

        initialPositions(board);
        initialPieces(board);

        return new Board(board);
    }

    private static void initialPositions(Map<Position, Piece> board) {
        List<Position> positions = Stream.of(Rank.values())
                .flatMap(rank ->
                        Stream.of(File.values()).map(file -> new Position(file, rank)))
                .collect(Collectors.toList());

        for (Position position : positions) {
            board.put(position, new EmptyPiece());
        }
    }

    private static void initialPieces(Map<Position, Piece> board) {
        initialPiecesWithoutPawn(board, Rank.EIGHT, Color.BLACK);
        initialPawns(board, Rank.SEVEN, Color.BLACK);
        initialPiecesWithoutPawn(board, Rank.ONE, Color.WHITE);
        initialPawns(board, Rank.TWO, Color.WHITE);
    }

    private static void initialPiecesWithoutPawn(Map<Position, Piece> board, final Rank rank,
                                                 final Color color) {
        board.put(new Position(File.A, rank), new RookPiece(color));
        board.put(new Position(File.B, rank), new KnightPiece(color));
        board.put(new Position(File.C, rank), new BishopPiece(color));
        board.put(new Position(File.D, rank), new QueenPiece(color));
        board.put(new Position(File.E, rank), new KingPiece(color));
        board.put(new Position(File.F, rank), new BishopPiece(color));
        board.put(new Position(File.G, rank), new KnightPiece(color));
        board.put(new Position(File.H, rank), new RookPiece(color));
    }

    private static void initialPawns(Map<Position, Piece> board, final Rank rank,
                                     final Color color) {
        for (File file : File.values()) {
            board.put(new Position(file, rank), new PawnPiece(color));
        }
    }

    public Piece findPiece(final Position position) {
        return board.get(position);
    }

    public boolean isEmptyPosition(final Position position) {
        return board.get(position).equals(new EmptyPiece());
    }

    public void move(final Position from, final Position to) {
        board.put(to, board.get(from));
        board.put(from, new EmptyPiece());
    }

    public int countPiece(final Piece piece) {
        return (int) board.values().stream()
                .filter(value -> value.equals(piece))
                .count();
    }

    public int countPieceOnSameFile(final Piece piece, final File file) {
        return (int) board.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isSameFile(file))
                .filter(entry -> entry.getValue().equals(piece))
                .count();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(board));
    }
}
