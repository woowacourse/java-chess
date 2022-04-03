package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Name;
import java.util.Map;

public class MoveValidator {
    private static final String NO_MOVE_ERROR_MESSAGE = "이동할 수 없는 위치입니다.";
    protected void validateMove(Map<Position, Piece> squares,  Position from, Position to) {
        checkNotSameTeam(squares, from, to);
        checkCanMove(squares, from, to);
        checkRoute(squares, from, to);
    }

    private void checkNotSameTeam(Map<Position, Piece> squares, Position from, Position to) {
        if (squares.get(from).isSameTeam(squares.get(to))) {
            throw new IllegalArgumentException();
        }
    }

    private void checkCanMove(Map<Position, Piece> squares, Position from, Position to) {
        if (!squares.get(from).canMove(squares.get(to), from, to)
                && squares.get(to).getName() != Name.NONE) {
            throw new IllegalArgumentException(NO_MOVE_ERROR_MESSAGE);
        }
    }

    private void checkRoute(Map<Position, Piece> squares, Position from, Position to) {
        for (Position position : squares.get(from).calculateRoute(from, to)) {
            checkIsPiece(squares, position);
        }
    }

    private void checkIsPiece(Map<Position, Piece> squares, Position position) {
        if (squares.get(position).getName() != Name.NONE) {
            throw new IllegalArgumentException(NO_MOVE_ERROR_MESSAGE);
        }
    }
}
