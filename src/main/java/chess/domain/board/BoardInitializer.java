package chess.domain.board;

import chess.domain.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardInitializer {

    private static final int MINIMUM_BOARD_POSITION = 1;
    private static final int MAXIMUM_BOARD_POSITION = 8;

    private final Map<Position, Piece> initialPiecePositions;

    public BoardInitializer() {
        Map<Position, Piece> initialPiecePositions = generateEmptyBoard();
        initialPiecePositions.putAll(getWhitePieces());
        initialPiecePositions.putAll(getBlackPieces());
        this.initialPiecePositions = initialPiecePositions;
    }

    public Map<Position, Piece> initialize() {
        return new HashMap<>(initialPiecePositions);
    }

    private Map<Position, Piece> generateEmptyBoard() {
        return IntStream.rangeClosed(MINIMUM_BOARD_POSITION, MAXIMUM_BOARD_POSITION)
                .boxed()
                .flatMap(this::generateHorizontalLine)
                .collect(generateEntry());
    }

    private Stream<Position> generateHorizontalLine(int row) {
        return IntStream.rangeClosed(MINIMUM_BOARD_POSITION, MAXIMUM_BOARD_POSITION)
                .mapToObj(column -> new Position(column, row));
    }

    private Collector<Position, ?, Map<Position, Piece>> generateEntry() {
        return Collectors.toMap(
                position -> position,
                position -> new Piece(PieceType.EMPTY, Color.NONE)
        );
    }

    private Map<Position, Piece> getWhitePieces() {
        Map<Position, Piece> initialWhitePiecePositions = new HashMap<>();
        initialWhitePiecePositions.putAll(getNotPawnsPieces(Color.WHITE, 1));
        initialWhitePiecePositions.putAll(getPawnsPieces(Color.WHITE, 2));
        return initialWhitePiecePositions;
    }

    private Map<Position, Piece> getBlackPieces() {
        Map<Position, Piece> initialWhitePiecePositions = new HashMap<>();
        initialWhitePiecePositions.putAll(getNotPawnsPieces(Color.BLACK, 8));
        initialWhitePiecePositions.putAll(getPawnsPieces(Color.BLACK, 7));
        return initialWhitePiecePositions;
    }

    private Map<Position, Piece> getNotPawnsPieces(Color color, int row) {
        return Map.of(new Position(1, row), new Piece(PieceType.ROOK, color),
                new Position(2, row), new Piece(PieceType.KNIGHT, color),
                new Position(3, row), new Piece(PieceType.BISHOP, color),
                new Position(4, row), new Piece(PieceType.QUEEN, color),
                new Position(5, row), new Piece(PieceType.KING, color),
                new Position(6, row), new Piece(PieceType.BISHOP, color),
                new Position(7, row), new Piece(PieceType.KNIGHT, color),
                new Position(8, row), new Piece(PieceType.ROOK, color));
    }


    private Map<Position, Piece> getPawnsPieces(Color color, int row) {
        return Map.of(new Position(1, row), new Piece(PieceType.PAWN, color),
                new Position(2, row), new Piece(PieceType.PAWN, color),
                new Position(3, row), new Piece(PieceType.PAWN, color),
                new Position(4, row), new Piece(PieceType.PAWN, color),
                new Position(5, row), new Piece(PieceType.PAWN, color),
                new Position(6, row), new Piece(PieceType.PAWN, color),
                new Position(7, row), new Piece(PieceType.PAWN, color),
                new Position(8, row), new Piece(PieceType.PAWN, color));

    }

    public boolean isFirstMove(Position position, Piece piece) {
        Piece initialPiece = initialPiecePositions.getOrDefault(position, new Piece(PieceType.EMPTY, Color.NONE));
        return initialPiece.equals(piece);
    }
}
