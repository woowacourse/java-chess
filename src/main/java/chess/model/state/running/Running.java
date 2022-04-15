package chess.model.state.running;

import chess.model.Team;
import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.state.State;
import java.util.Map;

public abstract class Running implements State {

    protected static final int COMMAND_INDEX = 0;
    protected static final int SOURCE_OPTION_INDEX = 1;
    protected static final int TARGET_OPTION_INDEX = 2;

    protected final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public Map<Team, Double> getScores() {
        throw new IllegalArgumentException("[ERROR] 아직 게임이 종료되지 않아 점수를 확인 할 수 없습니다.");
    }

    @Override
    public Team getWinner() {
        throw new IllegalArgumentException("[ERROR] 아직 게임이 종료되지 않아 승자를 확인 할 수 없습니다.");
    }
}
