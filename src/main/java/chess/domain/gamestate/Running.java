package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class Running implements State {
    private static final String ERROR_ALREADY_STARTED = "이미 시작되었습니다.";
    private static final String ERROR_CANT_LOAD = "게임 진행 중에는 게임을 불러올 수 없습니다.";
    private static final String ERROR_NO_WINNER = "게임 진행 중에는 승패를 판정할 수 없습니다.";

    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(ERROR_ALREADY_STARTED);
    }

    @Override
    public State load(Map<Position, Piece> board, boolean whiteTurn) {
        throw new UnsupportedOperationException(ERROR_CANT_LOAD);
    }

    @Override
    public State move(Position sourcePosition, Position targetPosition) {
        this.board.move(sourcePosition, targetPosition);
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
    public boolean isFinished() {
        return false;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Map<Camp, Score> getScores() {
        return Score.of(this.board);
    }

    @Override
    public Camp getWinner() {
        throw new UnsupportedOperationException(ERROR_NO_WINNER);
    }
}
