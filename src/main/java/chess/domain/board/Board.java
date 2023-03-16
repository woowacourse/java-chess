package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Square, Piece> board;

    private Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public static Board generate() {
        return new Board(initBoard());
    }

    private static Map<Square, Piece> initBoard() {
        final Map<Square, Piece> board = new HashMap<>();
        initialPieceWithoutPawn(board, Rank.EIGHT, Color.BLACK);
        initialPawn(board, Rank.SEVEN, Color.BLACK);
        initialPawn(board, Rank.TWO, Color.WHITE);
        initialPieceWithoutPawn(board, Rank.ONE, Color.WHITE);
        return board;
    }

    private static void initialPieceWithoutPawn(final Map<Square, Piece> board, final Rank rank, final Color color) {
        board.put(new Square(File.A, rank), new Rook(color));
        board.put(new Square(File.B, rank), new Knight(color));
        board.put(new Square(File.C, rank), new Bishop(color));
        board.put(new Square(File.D, rank), new Queen(color));
        board.put(new Square(File.E, rank), new King(color));
        board.put(new Square(File.F, rank), new Bishop(color));
        board.put(new Square(File.G, rank), new Knight(color));
        board.put(new Square(File.H, rank), new Rook(color));
    }

    private static void initialPawn(final Map<Square, Piece> board, final Rank rank, final Color color) {
        for (final File file : File.values()) {
            board.put(new Square(file, rank), new Pawn(color));
        }
    }

    public Optional<Piece> findPieceOf(final Square square) {
        return Optional.ofNullable(board.get(square));
    }

    public Map<Square, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public boolean canMove(final Square source, final List<Square> routes) {
        final boolean isExistHurdle = isExistHurdle(routes.subList(0, routes.size() - 1));
        final Square lastSquare = routes.get(routes.size() - 1);
        return !isExistHurdle && !isSameColor(source, lastSquare);
    }

    private boolean isExistHurdle(final List<Square> squares) {
        return squares.stream()
                .anyMatch(square -> board.containsKey(square));
    }

    private boolean isSameColor(final Square source, final Square lastSquare) {
        return board.containsKey(lastSquare) && !(board.get(source).isBlack() ^ board.get(lastSquare).isBlack());
    }

    public boolean canMovePawn(final Square source, final List<Square> routes) {
        final boolean isInitialMove = isInitialMove(source, routes);
        final boolean isDiagonalMovable = isDiagonalMovable(source, routes.get(0));
        return isInitialMove && isDiagonalMovable;
    }

    private boolean isInitialMove(final Square source, final List<Square> routes) {
        final Piece piece = board.get(source);
        final Square destination = routes.get(0);
        final int distanceY = Math.abs(destination.calculateDistanceY(source));
        return (distanceY == 2 && source.isInitPawnPosition(piece.isBlack())) || distanceY == 1;
    }

    private boolean isDiagonalMovable(final Square source, final Square destination) {
        return (isDiagonalUnit(source, destination) && isSameColor(source, destination))
                || (!isDiagonalUnit(source, destination) && !board.containsKey(destination));
    }

    private boolean isDiagonalUnit(final Square source, final Square destination) {
        final int distanceX = destination.calculateDistanceX(source);
        final int distanceY = destination.calculateDistanceY(source);
        return Math.abs(distanceX) == Math.abs(distanceY) && Math.abs(distanceX) == 1;
    }

    public void move(final Square source, final Square destination) {
        board.put(destination, board.remove(source));
    }
}
