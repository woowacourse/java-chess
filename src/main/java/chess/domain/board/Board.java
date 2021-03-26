package chess.domain.board;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Column;
import chess.domain.position.Notation;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Board {

    private final Map<Notation, Position> positions;

    private Board(Map<Notation, Position> positions) {
        this.positions = positions;
    }

    // TODO
    public static Board ofEmpty() {
        return new Board(new HashMap<>());
    }

    public Board movePiece(final Notation sourceNotation, final Notation targetNotation) {
        final Map<Notation, Position> nextBoardPositions = new HashMap<>(positions);
        final Position source = positions.get(sourceNotation);
        final Position target = positions.get(targetNotation);
        nextBoardPositions.replace(targetNotation, target.copyPieceFrom(source));
        nextBoardPositions.replace(sourceNotation, source.replacePiece(EmptyPiece.getInstance()));
        return new Board(nextBoardPositions);
    }

    public boolean hasAvailablePath(final Notation sourceNotation, final Notation targetNotation) {
        return pathsOf(sourceNotation).contains(targetNotation);
    }

    public List<Piece> remainingPieces() {
        return positions.values()
                .stream()
                .map(Position::holdingPiece)
                .filter(piece -> !piece.isEmpty())
                .collect(Collectors.toList())
                ;
    }

    public boolean hasPieceColor(final Notation sourceNotation, final PieceColor color) {
        final Position source = positions.get(sourceNotation);
        return source.holdingPieceIsColor(color);
    }

    public int kingCount() {
        return (int) positions.values()
                .stream()
                .filter(Position::containsKing)
                .count();
    }

    public int pawnCount(final Column column, final PieceColor color) {
        return (int) positions.values()
                .stream()
                .filter(position -> position.isOfColumn(column))
                .filter(Position::containsPawn)
                .filter(position -> position.holdingPieceIsColor(color))
                .count()
                ;
    }

    private Paths pathsOf(final Notation sourceNotation) {
        final Position sourcePosition = positions.get(sourceNotation);
        return sourcePosition.availablePaths(positions);
    }
}
