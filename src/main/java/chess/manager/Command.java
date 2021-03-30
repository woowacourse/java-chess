package chess.manager;

import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.view.OutputView;

import java.util.Arrays;

public enum Command {
    START("start", 1) {
        @Override
        public void execute(ChessManager chessManager, String input) {
            OutputView.printBoard(chessManager.getBoard());
        }
    },
    RESTART("restart", 1) {
        @Override
        public void execute(ChessManager chessManager, String input) {
            chessManager.resetBoard();
            OutputView.printRestartGame(chessManager.getBoard());
        }
    },
    END("end", 1) {
        @Override
        public void execute(ChessManager chessManager, String input) {
            OutputView.printEndGame();
        }
    },
    MOVE("move", 3) {
        @Override
        public void execute(ChessManager chessManager, String input) {
            chessManager.move(MoveCommand.of(input));
            OutputView.printBoard(chessManager.getBoard());
        }
    },
    STATUS("status", 1) {
        @Override
        public void execute(ChessManager chessManager, String input) {
            OutputView.printStatus(chessManager.getStatus());
        }
    },
    SHOW("show", 2) {
        @Override
        public void execute(ChessManager chessManager, String input) {
            OutputView.printAbleToMove(chessManager.getBoard(), chessManager.getReachablePositions(ShowCommand.of(input)));
        }
    };

    private final String command;
    private final int parameterCount;

    Command(final String command, final int parameterCount) {
        this.command = command;
        this.parameterCount = parameterCount;
    }

    public abstract void execute(ChessManager chessManager, String input);

    public static Command of(final String line) {
        String[] splitLine = line.split(" ");
        return Arrays.stream(values())
                .filter(command -> command.isSameCommand(splitLine[0]) && command.isSameParameterSize(splitLine.length))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령입니다."));
    }

    private boolean isSameCommand(final String input) {
        return this.command.equals(input);
    }

    private boolean isSameParameterSize(final int size) {
        return this.parameterCount == size;
    }

    public void isFirstCommand() {
        if (this.isStart() || this.isEnd()) {
            return;
        }
        throw new IllegalArgumentException("첫 입력은 start(게임시작) 또는 end(게임종료)만 가능합니다.");
    }

    public boolean isEnd() {
        return this.equals(END);
    }

    public boolean isStart() {
        return this.equals(START);
    }
}
