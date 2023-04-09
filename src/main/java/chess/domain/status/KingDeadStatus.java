package chess.domain.status;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class KingDeadStatus implements GameStatus {

    private final Color winner;

    public KingDeadStatus(final Color winner) {
        this.winner = winner;
    }

    @Override
    public void validatePlayerTurn(ChessBoard chessBoard, Position from) {
        throw new UnsupportedOperationException("게임이 진행 상태가 아닙니다");
    }

    @Override
    public GameStatus nextStatus(final Piece capturedPiece) {
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
        return getWinner();
    }
}
