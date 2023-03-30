package chess.status;

import chess.chessboard.ChessBoard;
import chess.chessboard.Position;
import chess.chessboard.Side;

public class KingDeadStatus implements GameStatus {

    private final Side winner;

    public KingDeadStatus(final Side winner) {
        this.winner = winner;
    }

    @Override
    public void validateMove(final ChessBoard chessBoard, final Position from, final Position to) {
        throw new UnsupportedOperationException("게임이 진행 상태가 아닙니다");
    }

    @Override
    public GameStatus nextStatus(final ChessBoard chessBoard) {
        return this;
    }

    @Override
    public boolean isGameOver() {
        return true;
    }

    @Override
    public Side getWinner() {
        return winner;
    }
}
