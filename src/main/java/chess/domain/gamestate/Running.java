package chess.domain.gamestate;

import chess.domain.Result;
import chess.domain.board.Board;
import chess.domain.board.BoardStatusCalculator;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class Running implements State {
    private static final String DONT_START_WHEN_RUNNING = "진행 중일 때는 시작할 수 없습니다.";
    private static final String CANT_GET_RESULT_WHEN_NOW = "아직 승패를 판정할 수 없습니다.";
    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalStateException(DONT_START_WHEN_RUNNING);
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
        return new BoardStatusCalculator(board).calculate(Piece::isBlack);
    }

    @Override
    public double statusOfWhite() {
        return new BoardStatusCalculator(board).calculate(piece -> !piece.isBlack());
    }

    @Override
    public Result getResult() {
        throw new IllegalStateException(CANT_GET_RESULT_WHEN_NOW);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }
}
