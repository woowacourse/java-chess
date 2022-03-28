package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.board.Board;
import chess.domain.board.Position;

public class Finished implements State {
    private static final String ERROR_CANT_MOVE = "게임이 종료되어 기물을 이동할 수 없습니다.";

    private final Board board;

    public Finished(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        return new Running(new Board());
    }

    @Override
    public State move(Position sourcePosition, Position targetPosition) {
        throw new IllegalStateException(ERROR_CANT_MOVE);
    }

    @Override
    public State end() {
        return this;
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
        return true;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Camp getWinner() {
        Camp winnerByKing = this.board.winnerByKing();
        if (winnerByKing != Camp.NONE) {
            return winnerByKing;
        }
        return this.board.winnerByScore();
    }
}
