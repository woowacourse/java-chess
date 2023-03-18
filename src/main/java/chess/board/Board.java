package chess.board;

import chess.path.Path;
import chess.piece.Color;
import chess.piece.Piece;
import chess.position.Position;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, Piece> board() {
        return board;
    }

    public void move(Position from, Position to, final Color turn) {
        validateCanMove(from, to, turn);
        movePiece(from, to);
    }

    private void validateCanMove(final Position from, final Position to, final Color turn) {
        validateSamePosition(from, to);
        validateIsFromEmpty(from);
        validateIsDifferentColor(from, turn);

        Path path = board.get(from)
                .searchPathTo(from, to, Optional.ofNullable(board.get(to)));
        path.validateObstacle(board.keySet());
    }

    private static void validateSamePosition(final Position from, final Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("말을 다른 곳으로 이동시켜 주세요");
        }
    }

    private void validateIsDifferentColor(final Position from, final Color turn) {
        if (!board.get(from).isSameColor(turn)) {
            throw new IllegalArgumentException("차례에 맞는 말을 선택해 주세요");
        }
    }

    private void validateIsFromEmpty(final Position from) {
        if (!board.containsKey(from)) {
            throw new IllegalArgumentException("출발점에 말이 없습니다.");
        }
    }

    private void movePiece(final Position from, final Position to) {
        Piece piece = board.remove(from);
        board.put(to, piece);
    }
}
