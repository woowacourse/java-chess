package chess.model.state;

import chess.model.board.Board;
import chess.model.board.GameResult;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.state.running.WhiteTurn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Ready implements State {

    public static final int COMMAND_INDEX = 0;
    private final Board board;

    public Ready() {
        this.board = new Board();
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
    public State proceed(List<String> inputs) {
        Command command = Command.of(inputs.get(COMMAND_INDEX));
        if (command.isStart()) {
            return new WhiteTurn(board);
        }
        throw new IllegalArgumentException("[ERROR] 게임을 시작하기 위한 명령어가 아닙니다.");
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return new HashMap<>();
    }

    @Override
    public Map<String, Piece> getBoardForWeb() {
        return new HashMap<>();
    }

    @Override
    public GameResult getScore() {
        throw new IllegalArgumentException("[ERROR] 아직 게임이 시작되지 않아 점수를 계산 할 수 없습니다.");
    }
}
