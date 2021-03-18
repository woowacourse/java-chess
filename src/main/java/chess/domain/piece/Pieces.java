package chess.domain.piece;

import chess.exception.NoSuchPermittedChessPieceException;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public Piece findPieceByPosition(final Color color, final Position source) {
        validateControllablePiece(color, source);
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(source))
                .findAny()
                .orElseThrow(NoSuchPermittedChessPieceException::new);
    }

    private void validateControllablePiece(final Color color, final Position source) {
        Optional<Piece> sourcePiece = pieces.stream()
                .filter(piece -> piece.isSamePosition(source))
                .findAny();

        if (!(sourcePiece.isPresent() && sourcePiece.get().getColor() == color)) {
            throw new NoSuchPermittedChessPieceException();
        }
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public void catchPiece(final Color color) {
        Set<Map.Entry<Position, Long>> duplicatePosition = findDuplicatePosition();


        removeIfExistDuplicatePositionByColor(color, duplicatePosition);
    }

    private void removeIfExistDuplicatePositionByColor(final Color color,
                                                       final Set<Map.Entry<Position, Long>> duplicatePosition) {
        duplicatePosition.stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .findAny()
                .ifPresent(position -> pieces.removeIf(duplicateCondition(color, position)));
    }

    private Predicate<Piece> duplicateCondition(final Color color, final Position position) {
        return piece -> piece.isSamePosition(position) &&
                !piece.isSameColor(color);
    }

    private Set<Map.Entry<Position, Long>> findDuplicatePosition() {
        return pieces.stream()
                .collect(groupingBy(piece -> new Position(piece.getRow(), piece.getColumn()), counting()))
                .entrySet();

    }
}
