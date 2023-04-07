package chess.domain.status;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class KingDeadStatus implements GameStatus {

    private final Color winner;

    public KingDeadStatus(final Color winner) {
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
    public Color getWinner() {
        return winner;
    }

    @Override
    public Color getTurn() {
        return winner;
    }
}
