package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;

public final class Ready implements State {
    @Override
    public State start() {
        return new White();
    }

    @Override
    public State stop() {
        return new End();
    }

    @Override
    public State changeTurn(Command command, ChessBoard chessBoard) {
        return new Ready();
    }

    @Override
    public double calculateBlackScore(ChessBoard chessBoard) {
        throw new IllegalArgumentException("지금은 점수를 계산할 수 없습니다.");
    }

    @Override
    public double calculateWhiteScore(ChessBoard chessBoard) {
        throw new IllegalArgumentException("지금은 점수를 계산할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
