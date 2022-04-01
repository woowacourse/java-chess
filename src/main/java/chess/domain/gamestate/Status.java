package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.StatusScore;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Piece;
import java.util.Map;

public final class Status implements State {
    private final Board board;
    private final Camp camp;

    public Status(final Board board, final Camp camp) {
        this.board = board;
        this.camp = camp;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("Status#start not implemented.");
    }

    @Override
    public State move(final Positions positions) {
        throw new UnsupportedOperationException("Status#move not implemented.");
    }

    @Override
    public State move(final Position before, final Position after) {
        throw new UnsupportedOperationException("Status#move not implemented.");
    }

    @Override
    public Camp switchCamp() {
        throw new UnsupportedOperationException("Status#switchCamp not implemented.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("Status#end not implemented.");
    }

    @Override
    public StatusScore calculateStatus() {
        return StatusScore.from(board);
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException("Status#getBoard not implemented.");
    }

    @Override
    public boolean isRunning() {
        throw new UnsupportedOperationException("Status#isRunning not implemented.");
    }

    @Override
    public boolean isFinished() {
        throw new UnsupportedOperationException("Status#isFinished not implemented.");
    }

    @Override
    public State status() {
        throw new IllegalStateException("status 확인 중에 다시 status를 확인할 수 없습니다.");
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public State returnState() {
        return new Running(board, camp);
    }
}
