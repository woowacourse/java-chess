package controller;

import command.play.PlayAction;
import command.play.PlayCommandType;
import command.start.StartAction;
import command.start.StartCommandType;
import domain.ChessGame;
import java.util.List;
import java.util.Objects;
import view.InputView;
import view.OutputView;

public final class ChessController {
    public ChessController() {
    }

    public void run() {
        StartAction startAction = inputStartCommand();
        ChessGame chessGame = startAction.init();
        if (Objects.nonNull(chessGame)) {
            OutputView.printBoard(chessGame.getPieces());
            play(chessGame);
        }
        OutputView.printEndedGameMessage();
    }

    private StartAction inputStartCommand() {
        List<String> command;
        while (validateInputStartCommand(command = InputView.readStartGameCommand())) {
        }
        return StartCommandType.from(command);
    }

    private boolean validateInputStartCommand(List<String> command) {
        try {
            StartCommandType.from(command);
            return false;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return true;
        }
    }

    private void play(final ChessGame chessGame) {
        boolean nextStep;
        do {
            nextStep = executeCommand(readMoveCommand(), chessGame);
        } while (nextStep);
    }

    private boolean executeCommand(final PlayAction command, final ChessGame chessGame) {
        try {
            return command.execute(chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
        return true;
    }

    private PlayAction readMoveCommand() {
        List<String> commands;
        while (validateInputMoveCommand(commands = InputView.readPlayGameOption())) {
        }
        return PlayCommandType.from(commands);
    }

    private boolean validateInputMoveCommand(List<String> commands) {
        try {
            PlayCommandType.from(commands);
            return false;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return true;
        }
    }
}
