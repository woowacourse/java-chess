package chess.model;

import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.rule.Rule;
import chess.model.unit.Piece;

public class Play {
    private static final String SIDE_MISMATCH = "자신의 말이 아닙니다.";
    private static final String NOT_VALID_MOVE = "이동규칙에 어긋납니다.";

    private final Board board;

    public Play(final Board board) {
        this.board = board;
    }

    public void movePiece(final Side side, final Square target, final Square destination) throws IllegalArgumentException {
        final Piece piece = board.getPiece(target);
        checkValidMove(side, target, destination, piece);
        board.setPiece(destination, piece);
        board.removePiece(target);
    }

    private void checkValidMove(final Side side, final Square target, final Square destination, final Piece piece) {
        if (piece.getSide() != side) {
            throw new IllegalArgumentException(SIDE_MISMATCH);
        }
        if (!Rule.isValidMove(board, target, destination)) {
            throw new IllegalArgumentException(NOT_VALID_MOVE);
        }
    }

}
