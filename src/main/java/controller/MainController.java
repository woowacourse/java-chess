package controller;

import domain.ChessGame;
import domain.Square;
import java.util.EnumMap;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class MainController {

    private final ChessGame chessGame;
    private final Map<ExecuteState, GameExecutor> commandMapper;
    private ExecuteState executeState;


    public MainController() {
        this.chessGame = new ChessGame();
        commandMapper = new EnumMap<>(ExecuteState.class);
        commandMapper.put(ExecuteState.START, none -> start());
        commandMapper.put(ExecuteState.MOVE, this::move);
        commandMapper.put(ExecuteState.END, GameExecutor.FINISH);
        executeState = ExecuteState.INIT;
    }

    public void run() {
        InputView.printStartMessage();
        while (executeState != ExecuteState.END) {
            executeState = play();
        }
    }

    private ExecuteState play() {
        try {
            Command command = Command.of(InputView.readCommand());
            ExecuteState executeState = command.toExecuteState(this.executeState);
            GameExecutor gameExecutor = commandMapper.get(executeState);
            gameExecutor.execute(command);
            return executeState;
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printError(e);
            return executeState;
        }
    }

    private void start() {
        OutputView.printChessBoard(chessGame);
    }

    private void move(Command command) {
        Square source = command.getSourceSquare();
        Square destination = command.getDestinationSquare();
        chessGame.move(source, destination);
        OutputView.printChessBoard(chessGame);
    }
}
