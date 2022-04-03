package chess.model.state.running;

import chess.model.board.Board;
import chess.model.board.GameResult;
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
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public Map<String, Piece> getBoardForWeb() {
        return board.getBoardForWeb();
    }

    @Override
    public GameResult getScore() {
        throw new IllegalArgumentException("[ERROR] 아직 게임이 종료되지 않아 점수를 계산 할 수 없습니다.");
    }
}
