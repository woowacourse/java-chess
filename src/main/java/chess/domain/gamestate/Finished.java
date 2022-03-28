package chess.domain.gamestate;

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
        return board.calculateScoreOfBlack();
    }

    @Override
    public double statusOfWhite() {
        return board.calculateScoreOfWhite();
    }

    @Override
    public int hasBlackWon() {
        if (this.board.hasBlackKingCaptured()){
            return -1;
        }
        if (this.board.hasWhiteKingCaptured()) {
            return 1;
        }
        return Double.compare(this.board.calculateScoreOfBlack(), this.board.calculateScoreOfWhite());
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
