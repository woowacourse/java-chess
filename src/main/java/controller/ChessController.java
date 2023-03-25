package controller;

import static command.MoveCommand.END;

import command.MoveCommandParser;
import command.StartCommand;
import domain.ChessGame;
import gameinitializer.InitialChessAlignment;
import java.util.List;
import view.InputView;
import view.OutputView;

public final class ChessController {
    private ChessGame chessGame;

    public ChessController() {
    }

    public void run() {
        StartCommand startCommand = inputStartCommand();
        if (StartCommand.START.equals(startCommand)) {
            chessGame = ChessGame.initGame(new InitialChessAlignment());
            OutputView.printBoard(chessGame.getPieces());
            play();
        }
        OutputView.printEndedGameMessage();
    }

    private StartCommand inputStartCommand() {
        String command;
        while (validateInputStartCommand(command = InputView.readStartGameOption())) {
        }
        return StartCommand.from(command);
    }

    private boolean validateInputStartCommand(String command) {
        try {
            StartCommand.from(command);
            return false;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return true;
        }
    }

    private void play() {
        boolean nextStep;
        do {
            nextStep = executeCommand();
        } while (nextStep);
    }

    private boolean executeCommand() {
        MoveCommandParser command = readMoveCommand();
        if (END.equals(command.getMoveCommand())) {
            return false;
        }
        boolean isEndedGame = movePieceWithHandling(command);
        boolean keepGame = !isEndedGame;
        OutputView.printBoard(chessGame.getPieces());
        return keepGame;
    }

    private boolean movePieceWithHandling(final MoveCommandParser command) {
        try {
            return chessGame.movePiece(command.getSource(), command.getDestination());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return false;
        }
    }

    private MoveCommandParser readMoveCommand() {
        List<String> commands;
        while (validateInputMoveCommand(commands = InputView.readPlayGameOption())) {
        }
        return MoveCommandParser.parse(commands);
    }

    private boolean validateInputMoveCommand(List<String> commands) {
        try {
            MoveCommandParser.parse(commands);
            return false;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return true;
        }
    }
}
