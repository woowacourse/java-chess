package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

public final class BlackTurn extends Running {

    private static final String NOT_BLACK_TURN = "[ERROR] BLACK 차례이므로, WHITE 말을 움직일 수 없습니다.";

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public ChessGame movePiece(Position fromPosition, Position toPosition) {
        if (board.getBoard().get(fromPosition).isSameColor(Color.WHITE)) {
            throw new IllegalStateException(NOT_BLACK_TURN);
        }
        board.movePiece(fromPosition, toPosition);
        if (!board.isAllKingExist()) {
            return new Complete(board);
        }
        return new WhiteTurn(board);
    }
}
