package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Kind;
import chess.domain.piece.Piece;

import java.util.Map;
import java.util.Set;

public final class Square {

    private static final String TURN_EXCEPTION_MESSAGE = "의 턴입니다.";
    private static final String CAN_NOT_MOVE_TO_SAME_COLOR_EXCEPTION_MESSAGE = "자신의 기물이 있는 곳으로 이동할 수 없습니다.";
    private static final String EMPTY_SOURCE_EXCEPTION_MESSAGE = "움직이려는 기물의 위치는 빈 공간입니다.";

    private Piece piece;

    public Square(final Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return piece.isEmpty();
    }

    public Set<Position> computePath(Position source, Position target, Square targetSquare, Color color) {
        validateEmpty();
        validateTurn(color);
        validateSameColor(targetSquare);
        return piece.computePathWithValidate(source, target);
    }

    private void validateEmpty() {
        if (piece.equalsColor(Color.NONE)) {
            throw new IllegalArgumentException(EMPTY_SOURCE_EXCEPTION_MESSAGE);
        }
    }

    private void validateTurn(final Color color) {
        if (!piece.equalsColor(color)) {
            throw new IllegalArgumentException(color.name() + TURN_EXCEPTION_MESSAGE);
        }
    }

    private void validateSameColor(final Square targetSquare) {
        if (piece.equalsColor(targetSquare.piece)) {
            throw new UnsupportedOperationException(CAN_NOT_MOVE_TO_SAME_COLOR_EXCEPTION_MESSAGE);
        }
    }

    public boolean canMovePiece(Map<Position, Boolean> isEmptyPath, Position source, Position target) {
        return piece.canMoveWithValidate(isEmptyPath, source, target);
    }

    public void changePiece(Square other) {
        this.piece = other.piece;
        other.makeEmpty();
    }

    public void makeEmpty() {
        this.piece = new Empty();
    }

    public Piece getPiece() {
        return piece;
    }

    public Kind pieceKind() {
        return piece.getKind();
    }

    public boolean equalsColor(Color color) {
        return piece.equalsColor(color);
    }
}
