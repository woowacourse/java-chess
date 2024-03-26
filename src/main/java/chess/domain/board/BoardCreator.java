package chess.domain.board;

import chess.domain.Position;
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
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardCreator {

    private static final int MINIMUM_FILE = 1;
    private static final int MAXIMUM_FILE = 8;
    private static final int WHITE_PIECE_START_RANK = 1;
    private static final int WHITE_PAWN_START_RANK = 2;
    private static final int BLACK_PIECE_START_RANK = 8;
    private static final int BLACK_PAWN_START_RANK = 7;
    private static final Color START_COLOR = Color.WHITE;
    private static final Empty EMPTY = new Empty();

    public static Board create() {
        Map<Position, Piece> initialPiecePositions = generateEmptyBoard();
        initialPiecePositions.putAll(getWhitePieces());
        initialPiecePositions.putAll(getBlackPieces());
        return new Board(initialPiecePositions, START_COLOR);
    }

    private static Map<Position, Piece> generateEmptyBoard() {
        return IntStream.rangeClosed(MINIMUM_FILE, MAXIMUM_FILE)
                .boxed()
                .flatMap(BoardCreator::generateHorizontalLine)
                .collect(generateEntry());
    }

    private static Stream<Position> generateHorizontalLine(final int rank) {
        return IntStream.rangeClosed(MINIMUM_FILE, MAXIMUM_FILE)
                .mapToObj(file -> new Position(file, rank));
    }

    private static Collector<Position, ?, Map<Position, Piece>> generateEntry() {
        return Collectors.toMap(position -> position, position -> EMPTY);
    }

    private static Map<Position, Piece> getWhitePieces() {
        Map<Position, Piece> initialWhitePiecePositions = new HashMap<>();
        initialWhitePiecePositions.putAll(getNotPawnsPieces(Color.WHITE, WHITE_PIECE_START_RANK));
        initialWhitePiecePositions.putAll(getPawnsPieces(Color.WHITE, WHITE_PAWN_START_RANK));
        return initialWhitePiecePositions;
    }

    private static Map<Position, Piece> getBlackPieces() {
        Map<Position, Piece> initialWhitePiecePositions = new HashMap<>();
        initialWhitePiecePositions.putAll(getNotPawnsPieces(Color.BLACK, BLACK_PIECE_START_RANK));
        initialWhitePiecePositions.putAll(getPawnsPieces(Color.BLACK, BLACK_PAWN_START_RANK));
        return initialWhitePiecePositions;
    }

    private static Map<Position, Piece> getNotPawnsPieces(final Color color, final int rank) {
        return Map.of(new Position(1, rank), new Rook(color),
                new Position(2, rank), new Knight(color),
                new Position(3, rank), new Bishop(color),
                new Position(4, rank), new Queen(color),
                new Position(5, rank), new King(color),
                new Position(6, rank), new Bishop(color),
                new Position(7, rank), new Knight(color),
                new Position(8, rank), new Rook(color));
    }

    private static Map<Position, Piece> getPawnsPieces(final Color color, final int rank) {
        return IntStream.rangeClosed(MINIMUM_FILE, MAXIMUM_FILE)
                .boxed()
                .collect(Collectors.toMap(file -> new Position(file, rank), file -> new Pawn(color)));
    }
}
