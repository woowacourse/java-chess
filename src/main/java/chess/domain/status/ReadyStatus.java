package chess.domain.status;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class ReadyStatus implements GameStatus {
    @Override
    public void validateMove(final ChessBoard chessBoard, final Position from, final Position to) {
        throw new IllegalArgumentException("게임이 진행 중이지 않습니다");
    }

    @Override
    public GameStatus nextStatus(final ChessBoard chessBoard) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isGameOver() {
        return true;
    }

    @Override
    public Color getWinner() {
        throw new IllegalArgumentException("");
    }

    @Override
    public Color getTurn() {
        throw new UnsupportedOperationException();
    }
}
