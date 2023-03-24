package chess.controller;

import chess.domain.command.*;
import chess.service.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;
    private final Map<String, Command> commands;

    public ChessController(final InputView inputView, final OutputView outputView, ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
        this.commands = new HashMap<>();
        setup(outputView, chessGame);
    }

    private void setup(OutputView outputView, ChessGame chessGame) {
        commands.put("start", new StartCommand(outputView, chessGame));
        commands.put("move", new MoveCommand(outputView, chessGame));
        commands.put("end", new EndCommand(outputView, chessGame));
        commands.put("status", new StatusCommand(outputView, chessGame));
    }

    public void run() {
        outputView.printStartMessage();
        while (chessGame.isContinue()) {
            repeat(this::playGame);
        }
    }

    private void playGame(final String inputCommand) {
        String command = findCommand(inputCommand);
        commands.getOrDefault(command, new ExceptionCommand()).execute(inputCommand);
    }

    private String findCommand(final String input) {
        if (input.contains(" ")) {
            int spaceIndex = input.indexOf(" ");
            return input.substring(0, spaceIndex);
        }
        return input;
    }

    private void repeat(final Consumer<String> playGame) {
        try {
            playGame.accept(inputView.readCommand());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            repeat(playGame);
        }
    }
}
