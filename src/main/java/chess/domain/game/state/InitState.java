package chess.domain.game.state;

import chess.domain.board.Square;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;

public class InitState implements ExecuteState {

    public static final InitState CACHE = new InitState();

    @Override
    public void start() {
    }

    @Override
    public double calculateScoreOfColor(final Color color, final ChessGame chessGame) {
        throw new IllegalStateException("초기 상태에서는 점수를 계산할 수 없습니다.");
    }

    @Override
    public void move(final Square source, final Square destination, final ChessGame chessGame) {
        throw new IllegalStateException("초기 상태에서는 움직일 수 없습니다.");
    }

    @Override
    public void end() {
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
