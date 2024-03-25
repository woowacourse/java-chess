package controller;

import domain.game.ChessGame;
import domain.game.MovePosition;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;
import view.Command;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Command, BiConsumer<ChessGame, MovePosition>> commands;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.commands = new EnumMap<>(Command.class);
    }

    public void run() {
        putCommands();
        play();
    }

    private void putCommands() {
        commands.put(Command.START, (chessGame, ignore) -> start(chessGame));
        commands.put(Command.MOVE, this::move);
        commands.put(Command.END, (chessGame, ignore) -> end(chessGame));
    }

    private void play() {
        outputView.printStartingMessage();
        ChessGame chessGame = new ChessGame();
        while (chessGame.isRunning()) {
            executeCommand(chessGame);
        }
    }

    private void executeCommand(ChessGame chessGame) {
        try {
            MovePosition movePosition = new MovePosition(inputView.readCommand());
            Command command = Command.from(movePosition);
            commands.get(command).accept(chessGame, movePosition);
        } catch (Exception e) {
            outputView.printErrorMessage(e);
        }
    }

    private void start(ChessGame chessGame) {
        chessGame.start();
        outputView.printBoard(chessGame.getBoard());
    }

    private void move(ChessGame chessGame, MovePosition movePosition) {
        chessGame.move(movePosition);
        outputView.printBoard(chessGame.getBoard());
    }

    private void end(ChessGame chessGame) {
        chessGame.end();
    }
}
