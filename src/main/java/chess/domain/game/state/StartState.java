package chess.domain.game.state;

import chess.domain.board.Square;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;

public class StartState implements ExecuteState {

    public static final StartState CACHE = new StartState();

    @Override
    public void start() {
        throw new IllegalStateException("시작 상태에서는 시작할 수 없습니다.");
    }

    @Override
    public double calculateScoreOfColor(final Color color, final ChessGame chessGame) {
        return chessGame.calculateScoreOfColor(color);
    }

    @Override
    public void move(final Square source, final Square destination, final ChessGame chessGame) {
        chessGame.movePiece(source, destination);
    }

    @Override
    public void end() {
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
