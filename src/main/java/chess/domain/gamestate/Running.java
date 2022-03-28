package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.board.Board;
import chess.domain.board.Position;

public class Running implements State {
    private static final String ERROR_ALREADY_STARTED = "이미 시작되었습니다.";
    private static final String ERROR_NO_WINNER = "게임 진행 중에는 승패를 판정할 수 없습니다.";

    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalStateException(ERROR_ALREADY_STARTED);
    }

    @Override
    public State move(Position beforePosition, Position afterPosition) {
        this.board.move(beforePosition, afterPosition);
        if (this.board.hasKingCaptured()) {
            return new Finished(this.board);
        }
        return this;
    }

    @Override
    public State end() {
        return new Finished(this.board);
    }

    @Override
    public double statusOfBlack() {
        return board.calculateScoreOf(Camp.BLACK);
    }

    @Override
    public double statusOfWhite() {
        return board.calculateScoreOf(Camp.WHITE);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Camp getWinner() {
        throw new IllegalStateException(ERROR_NO_WINNER);
    }
}
