package chess.domain.gamestate;

import chess.domain.Winner;
import chess.domain.board.Board;
import chess.domain.board.Position;

public class Finished implements State {
    private static final String INVALID_STATE_MOVE_EXCEPTION = "게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.";

    private final Board board;

    public Finished(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        return new Running(new Board());
    }

    @Override
    public State move(Position beforePosition, Position afterPosition) {
        throw new IllegalStateException(INVALID_STATE_MOVE_EXCEPTION);
    }

    @Override
    public State end() {
        return this;
    }

    @Override
    public double statusOfBlack() {
        return board.scoreOfBlack();
    }

    @Override
    public double statusOfWhite() {
        return board.scoreOfWhite();
    }

    @Override
    public Winner findWinner() {
        if (this.board.hasBlackKingCaptured()) {
            return Winner.WHITE;
        }
        if (this.board.hasWhiteKingCaptured()) {
            return Winner.BLACK;
        }
        return findWinnerByScore();
    }

    private Winner findWinnerByScore() {
        final int compared = Double.compare(this.board.scoreOfBlack(), this.board.scoreOfWhite());
        if (compared > 0) {
            return Winner.BLACK;
        }
        if (compared < 0) {
            return Winner.WHITE;
        }
        return Winner.DRAW;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }
}
