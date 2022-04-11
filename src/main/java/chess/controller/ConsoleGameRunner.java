package chess.controller;

import chess.view.command.Command;
import chess.view.command.CommandFactory;
import chess.view.InputView;
import chess.view.OutputView;
import chess.domain.GameManager;

public final class ConsoleGameRunner {

    public void run() {
        OutputView.printStartMessage();
        GameManager gameManager = new GameManager();
        Command currentCommand = initializeCommand();
        currentCommand.execute(gameManager);

        while (!currentCommand.isEnd()) {
            if (gameManager.isFinished()) {
                currentCommand.execute(gameManager);
                break;
            }
            currentCommand = inputCommandAndExecute(gameManager);
        }
    }

    private Command inputCommandAndExecute(GameManager gameManager) {
        try {
            String input = InputView.requestToInputCommand();
            Command currentCommand = CommandFactory.getInGameInstanceFrom(input);
            currentCommand.execute(gameManager);
            return currentCommand;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommandAndExecute(gameManager);
        }
    }

    private Command initializeCommand() {
        String input = InputView.requestToInputCommand();
        try {
            return CommandFactory.getPreGameInstanceFrom(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initializeCommand();
        }
    }
}
