package chess.domain.board;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
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
        initialPiecePositions.putAll(getPiecesByColor(WHITE));
        initialPiecePositions.putAll(getPiecesByColor(BLACK));
        this.initialPiecePositions = initialPiecePositions;
    }

    public Map<Position, Piece> initialize() {
        return new HashMap<>(initialPiecePositions);
    }

    private Map<Position, Piece> generateEmptyBoard() {
        return IntStream.rangeClosed(MINIMUM_BOARD_POSITION, MAXIMUM_BOARD_POSITION)
                .boxed()
                .flatMap(this::generateHorizontalLine)
                .collect(generateEntry(Piece.getEmptyPiece()));
    }

    private Stream<Position> generateHorizontalLine(final int rank) {
        return IntStream.rangeClosed(MINIMUM_BOARD_POSITION, MAXIMUM_BOARD_POSITION)
                .mapToObj(file -> Position.of(file, rank));
    }

    private Collector<Position, ?, Map<Position, Piece>> generateEntry(final Piece piece) {
        return Collectors.toMap(
                position -> position,
                position -> piece
        );
    }

    private Map<Position, Piece> getPiecesByColor(final Color color) {
        List<InitialPiecePosition> whitePieces = InitialPiecePosition.getInitialPositionByColor(color);
        return whitePieces.stream()
                .flatMap(initialPiecePosition -> initialPiecePosition.getInitialPositions().stream()
                        .collect(generateEntry(initialPiecePosition.getPiece())).entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, replacement) -> existing));
    }
}
