package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

public final class WhiteTurn extends Running{

    private static final String NOT_WHITE_TURN = "[ERROR] WHITE 차례이므로, BLACK 말을 움직일 수 없습니다.";

    public WhiteTurn(Board board) {
        super(board);
    }

    @Override
    public ChessGame movePiece(Position fromPosition, Position toPosition) {
        if (board.getBoard().get(fromPosition).isSameColor(Color.BLACK)) {
            throw new IllegalStateException(NOT_WHITE_TURN);
        }
        board.movePiece(fromPosition, toPosition);
        if (!board.isAllKingExist()) {
            return new Complete(board);
        }
        return new BlackTurn(board);
    }
}
