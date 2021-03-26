package chess.domain.board;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Coordinate;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Board {

    private final Map<Coordinate, Position> positions;

    private Board(Map<Coordinate, Position> positions) {
        this.positions = positions;
    }

    // TODO
    public static Board ofEmpty() {
        return new Board(new HashMap<>());
    }

    public Board movePiece(final Coordinate sourceCoordinate, final Coordinate targetCoordinate) {
        final Map<Coordinate, Position> nextBoardPositions = new HashMap<>(positions);
        final Position source = positions.get(sourceCoordinate);
        final Position target = positions.get(targetCoordinate);
        nextBoardPositions.replace(targetCoordinate, target.copyPieceFrom(source));
        nextBoardPositions.replace(sourceCoordinate, source.replacePiece(EmptyPiece.getInstance()));
        return new Board(nextBoardPositions);
    }

    public Position get(Coordinate target) {
        return positions.get(target);
    }

    public boolean hasAvailablePath(final Coordinate sourceCoordinate, final Coordinate targetCoordinate) {
        final Paths paths = new Paths(sourceCoordinate, positions);
        return paths.available(targetCoordinate);
    }

    public List<Piece> remainingPieces() {
        return positions.values()
                .stream()
                .map(position -> position.holdingPiece())
                .collect(Collectors.toList());
    }

    public boolean hasPieceColor(final Coordinate sourceCoordinate, final PieceColor enemyColor) {
        final Position source = positions.get(sourceCoordinate);
        return source.holdingPieceIsColor(enemyColor);
    }

    public
}
