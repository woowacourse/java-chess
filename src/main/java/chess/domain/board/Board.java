package chess.domain.board;

import chess.domain.piece.property.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class Board {

    private final List<Squares> board = new ArrayList<>();

    public Board() {
        board.add(Squares.initializePieces(Color.WHITE));
        board.add(Squares.initializePawns(Color.WHITE));
        board.add(Squares.initializeEmpty());
        board.add(Squares.initializeEmpty());
        board.add(Squares.initializeEmpty());
        board.add(Squares.initializeEmpty());
        board.add(Squares.initializePawns(Color.BLACK));
        board.add(Squares.initializePieces(Color.BLACK));
    }

    public void confirmMove(final Position source, final Position target, Color color) {
        Square sourceSquare = getSquare(source);
        Square targetSquare = getSquare(target);
        validateLegalSourceColor(sourceSquare, color);
        validateLegalTargetColor(sourceSquare, targetSquare);

        Set<Position> movablePath = sourceSquare.computePath(source, target);
        Map<Position, Boolean> isEmptySquare = generateIsEmptySquare(movablePath);

        validateMove(source, target, sourceSquare, isEmptySquare);
        move(sourceSquare, targetSquare);
    }

    private void move(final Square sourceSquare, final Square targetSquare) {
        targetSquare.changePiece(sourceSquare);
        sourceSquare.makeEmpty();
    }

    private void validateMove(final Position source, final Position target, final Square sourceSquare, final Map<Position, Boolean> isEmptySquare) {
        if (!sourceSquare.canMovePiece(isEmptySquare, source, target)) {
            throw new IllegalArgumentException("유효한 움직임이 아닙니다.");
        }
    }

    private void validateLegalSourceColor(final Square sourceSquare, final Color color) {
        if (sourceSquare.isEmpty()) {
            throw new IllegalArgumentException("비어있는 칸입니다.");
        }
        if (!sourceSquare.equalsColor(color)) {
            throw new IllegalArgumentException("움직일 수 있는 기물이 아닙니다.");
        }
    }

    private Map<Position, Boolean> generateIsEmptySquare(final Set<Position> movablePath) {
        return movablePath.stream()
                .collect(Collectors.toMap(
                        position -> position,
                        position -> getSquare(position).isEmpty()));
    }

    private void validateLegalTargetColor(final Square sourceSquare, final Square targetSquare) {
        if (sourceSquare.equalsColor(targetSquare)) {
            throw new UnsupportedOperationException("자신의 기물이 있는 곳으로 이동할 수 없습니다.");
        }
    }

    private Square getSquare(final Position source) {
        int rank = source.getRank();
        int file = source.getFile();
        Squares squares = board.get(rank);
        return squares.get(file);
    }

    public List<Squares> getSquares() {
        return new ArrayList<>(board);
    }
}
