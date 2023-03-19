package chess.domain.board;

import chess.domain.piece.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class Board {

    private static final String CAN_NOT_MOVE_EXCEPTION_MESSAGE = "유효한 움직임이 아닙니다.";
    private static final String TURN_EXCEPTION_MESSAGE = "의 턴입니다.";
    private static final String CAN_NOT_MOVE_TO_SAME_COLOR_EXCEPTION_MESSAGE = "자신의 기물이 있는 곳으로 이동할 수 없습니다.";
    private static final String EMPTY_SOURCE_EXCEPTION_MESSAGE = "움직이려는 기물의 위치는 빈 공간입니다.";

    private final List<Squares> board = new ArrayList<>();

    public Board() {
        board.add(Squares.initPieces(Color.WHITE));
        board.add(Squares.initWhitePawns());
        board.add(Squares.initEmpty());
        board.add(Squares.initEmpty());
        board.add(Squares.initEmpty());
        board.add(Squares.initEmpty());
        board.add(Squares.initBlackPawns());
        board.add(Squares.initPieces(Color.BLACK));
    }

    public void play(Position source, Position target, Color color) {
        Square sourceSquare = getSquare(source);
        Square targetSquare = getSquare(target);
        validateEmpty(sourceSquare);
        validateTurn(sourceSquare, color);
        validateSameColor(sourceSquare, targetSquare);
        Set<Position> movablePath = sourceSquare.computePath(source, target);
        Map<Position, Boolean> isEmptySquare = generateIsEmptySquare(movablePath);

        validateMove(source, target, sourceSquare, isEmptySquare);
        move(sourceSquare, targetSquare);
    }

    private void validateEmpty(Square sourceSquare) {
        if (sourceSquare.equalsColor(Color.NONE)) {
            throw new IllegalArgumentException(EMPTY_SOURCE_EXCEPTION_MESSAGE);
        }
    }

    private void validateTurn(final Square sourceSquare, final Color color) {
        if (!sourceSquare.equalsColor(color)) {
            throw new IllegalArgumentException(color.name() + TURN_EXCEPTION_MESSAGE);
        }
    }

    private void validateSameColor(final Square sourceSquare, final Square targetSquare) {
        if (sourceSquare.equalsColor(targetSquare)) {
            throw new UnsupportedOperationException(CAN_NOT_MOVE_TO_SAME_COLOR_EXCEPTION_MESSAGE);
        }
    }

    private void validateMove(final Position source, final Position target, final Square sourceSquare, final Map<Position, Boolean> isEmptySquare) {
        if (!sourceSquare.canMovePiece(isEmptySquare, source, target)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }

    private Map<Position, Boolean> generateIsEmptySquare(final Set<Position> movablePath) {
        return movablePath.stream()
                .collect(Collectors.toMap(
                        position -> position,
                        position -> getSquare(position).isEmpty()));
    }

    private void move(final Square sourceSquare, final Square targetSquare) {
        targetSquare.changePiece(sourceSquare);
        sourceSquare.makeEmpty();
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
