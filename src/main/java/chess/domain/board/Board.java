package chess.domain.board;

import chess.domain.piece.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class Board {

    private static final String CAN_NOT_MOVE_EXCEPTION_MESSAGE = "유효한 움직임이 아닙니다.";
    private static final String NOT_YOUR_TURN_EXCEPTION_MESSAGE = "의 턴이 아닙니다.";
    private static final String CAN_NOT_MOVE_TO_SAME_COLOR_EXCEPTION_MESSAGE = "자신의 기물이 있는 곳으로 이동할 수 없습니다.";

    private final List<Squares> board = new ArrayList<>();

    public Board() {
        board.add(Squares.initPieces(Color.WHITE));
        board.add(Squares.initPawns(Color.WHITE));
        board.add(Squares.initEmpty());
        board.add(Squares.initEmpty());
        board.add(Squares.initEmpty());
        board.add(Squares.initEmpty());
        board.add(Squares.initPawns(Color.BLACK));
        board.add(Squares.initPieces(Color.BLACK));
    }

    public void play(Position source, Position target, Color color) {
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
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }

    private void validateLegalSourceColor(final Square sourceSquare, final Color color) {
        if (!sourceSquare.equalsColor(color)) {
            throw new IllegalArgumentException(color.name() + NOT_YOUR_TURN_EXCEPTION_MESSAGE);
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
            throw new UnsupportedOperationException(CAN_NOT_MOVE_TO_SAME_COLOR_EXCEPTION_MESSAGE);
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
