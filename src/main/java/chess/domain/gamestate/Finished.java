package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.Result;
import chess.domain.board.Board;
import chess.domain.board.BoardStatusCalculator;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class Finished implements State {
    private static final int RESULT_CRITERIA = 0;
    private static final String CANT_MOVE_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.";

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
        throw new IllegalStateException(CANT_MOVE_WHEN_NOT_RUNNING);
    }

    @Override
    public Camp switchCamp() {
        throw new IllegalStateException(CANT_MOVE_WHEN_NOT_RUNNING);
    }

    @Override
    public State end() {
        return this;
    }

    @Override
    public double statusOfBlack() {
        return new BoardStatusCalculator(board).calculate(Piece::isBlack);
    }

    @Override
    public double statusOfWhite() {
        return new BoardStatusCalculator(board).calculate(piece -> !piece.isBlack());
    }

    @Override
    public Result getResult() {
        if (this.board.hasBlackKingCaptured()) {
            return Result.BLACK_LOSE;
        }
        if (this.board.hasWhiteKingCaptured()) {
            return Result.BLACK_WIN;
        }
        return getResultWhenNoKingCaptured();
    }

    private Result getResultWhenNoKingCaptured() {
        final int resultNumber = Double.compare(statusOfBlack(), statusOfWhite());
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
