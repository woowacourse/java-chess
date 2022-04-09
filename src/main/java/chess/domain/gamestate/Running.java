package chess.domain.gamestate;

import chess.domain.Color;
import chess.domain.Winner;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class Running implements State {
    private static final String INVALID_STATE_RUNNING_START_EXCEPTION = "진행 중일 때는 시작할 수 없습니다.";
    private static final String INVALID_STATE_RESULT_EXCEPTION = "아직 승패를 판정할 수 없습니다.";

    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalStateException(INVALID_STATE_RUNNING_START_EXCEPTION);
    }

    @Override
    public State move(Position beforePosition, Position afterPosition) {
        board.move(beforePosition, afterPosition);
        if (board.hasKingCaptured()) {
            return new Finished(board);
        }
        return this;
    }

    @Override
    public State end() {
        return new Finished(board);
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
        throw new IllegalStateException(INVALID_STATE_RESULT_EXCEPTION);
    }

    @Override
    public State load(Map<Position, Piece> board, Color turn) {
        throw new IllegalStateException("게임 진행중에는 로드할 수 없습니다.");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Color getTurn() {
        return board.getTurn();
    }
}
