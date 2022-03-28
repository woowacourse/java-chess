package chess.domain.gamestate;

import chess.domain.Result;
import chess.domain.board.Board;
import chess.domain.board.Position;

public class Finished implements State {
    private static final int RESULT_CRITERIA = 0;
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
        throw new IllegalStateException("게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.");
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
    public Result getResult() {
        if (this.board.hasBlackKingCaptured()) {
            return Result.BLACK_LOSE;
        }
        if (this.board.hasWhiteKingCaptured()) {
            return Result.BLACK_WIN;
        }
        return getResultWhenNoKingCaputerd();
    }

    private Result getResultWhenNoKingCaputerd() {
        final int resultNumber = Double.compare(this.board.calculateScoreOfBlack(), this.board.calculateScoreOfWhite());
        if (resultNumber > RESULT_CRITERIA) {
            return Result.BLACK_WIN;
        }
        if (resultNumber < RESULT_CRITERIA) {
            return Result.BLACK_LOSE;
        }
        return Result.DRAW;

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
