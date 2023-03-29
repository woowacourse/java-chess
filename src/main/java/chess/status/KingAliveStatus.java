package chess.status;

import chess.chessboard.ChessBoard;
import chess.chessboard.Position;
import chess.chessboard.Side;

public class KingAliveStatus implements GameStatus {

    private final Side turn;

    public KingAliveStatus(final Side turn) {
        this.turn = turn;
    }

    @Override
    public boolean isMoveValid(final ChessBoard chessBoard, final Position from, final Position to) {
        return false;
    }

    @Override
    public GameStatus nextStatus(final ChessBoard chessBoard) {
        if (chessBoard.isKingDead()) {
            return new KingDeadStatus(turn);
        }
        return new KingAliveStatus(turn.nextTurn());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public Side getWinner() {
        throw new UnsupportedOperationException("게임이 아직 진행 중입니다");
    }
}
