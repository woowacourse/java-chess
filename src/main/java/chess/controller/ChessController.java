package chess.controller;

import chess.domain.Position;
import chess.dto.PositionRequest;
import chess.game.ChessGame;
import chess.view.InputView;
import chess.view.PositionMapper;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class ChessController {
    private static final String END_COMMAND = "end";
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final int MOVE_COMMAND_SIZE = 3;

    private final ChessGame chessGame;

    public ChessController(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void run() {
        OutputView.printStartMessage();
        while (gameLoop() != GameStatus.EXIT) {
        }
    }

    private GameStatus gameLoop() {
        List<String> commands = repeat(InputView::readCommand);
        String command = commands.get(0);
        if (command.equals(END_COMMAND)) {
            return GameStatus.EXIT;
        }
        if (command.equals(START_COMMAND)) {
            createBoard();
        }
        if (command.equals(MOVE_COMMAND)) {
            execute(() -> move(commands));
        }
        return GameStatus.CONTINUE;
    }

    private void createBoard() {
        chessGame.create();
        OutputView.printBoard(chessGame.getBoard());
    }

    private void move(List<String> command) {
        validateMoveCommand(command);
        PositionRequest source = PositionMapper.map(command.get(1));
        PositionRequest target = PositionMapper.map(command.get(2));
        chessGame.move(Position.of(source.getX(), source.getY()), Position.of(target.getX(), target.getY()));
        OutputView.printBoard(chessGame.getBoard());
    }

    private void validateMoveCommand(List<String> command) {
        if (command.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("[ERROR] move (source) (target) 형식으로 입력해주세요.");
        }
    }

    public <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
            return repeat(supplier);
        }
    }

    public void execute(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
