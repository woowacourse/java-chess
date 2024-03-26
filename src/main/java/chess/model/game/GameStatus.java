package chess.model.game;

import chess.model.board.Board;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GameStatus {

    private static final int COMMAND_INDEX = 0;

    private final Status status;
    private final Consumer<Board> onStart;
    private final BiConsumer<List<String>, Board> onMove;

    public GameStatus(Consumer<Board> onStart, BiConsumer<List<String>, Board> onMove) {
        this(Status.READY, onStart, onMove);
    }

    private GameStatus(Status status, Consumer<Board> onStart,
        BiConsumer<List<String>, Board> onMove) {
        this.status = status;
        this.onStart = onStart;
        this.onMove = onMove;
    }

    public GameStatus action(List<String> commands, Board board) {
        Command command = Command.findCommand(commands.get(COMMAND_INDEX));
        if (command.isEnd()) {
            return changeEnd();
        }
        if (command.isStart()) {
            GameStatus gameStatus = changeStart();
            onStart.accept(board);
            return gameStatus;
        }
        if (command.isMove()) {
            GameStatus gameStatus = changeMove();
            onMove.accept(commands, board);
            return gameStatus;
        }
        return this;
    }

    private GameStatus changeEnd() {
        return new GameStatus(Status.END, onStart, onMove);
    }

    private GameStatus changeStart() {
        if (status.isReady()) {
            return new GameStatus(Status.START, onStart, onMove);
        }
        throw new UnsupportedOperationException("게임이 이미 진행 중 입니다.");
    }

    private GameStatus changeMove() {
        if (status.isMove()) {
            return this;
        }
        if (status.isStart()) {
            return new GameStatus(Status.MOVE, onStart, onMove);
        }
        throw new UnsupportedOperationException("게임을 start 해 주세요.");
    }

    public boolean isRunning() {
        return status.isRunning();
    }

    public boolean isReady() {
        return status.isReady();
    }

    public boolean isStarted() {
        return status.isStart();
    }

    public boolean isMoved() {
        return status.isMove();
    }
}
