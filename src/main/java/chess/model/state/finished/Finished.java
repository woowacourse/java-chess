package chess.model.state.finished;

import static chess.model.state.Ready.COMMAND_INDEX;

import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.state.Command;
import chess.model.state.State;
import chess.model.state.running.WhiteTurn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Finished implements State {

    protected final Board board;

    protected Finished(Board board) {
        this.board = board;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isWhiteTurn() {
        return false;
    }

    @Override
    public boolean isBlackTurn() {
        return false;
    }

    @Override
    public State proceed(List<String> inputs) {
        Command command = Command.of(inputs.get(COMMAND_INDEX));
        if (command.isStart()) {
            return new WhiteTurn(Board.init());
        }
        throw new IllegalArgumentException("[ERROR] 게임을 재시작 하기위한 명령어를 입력해주세요.");
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
