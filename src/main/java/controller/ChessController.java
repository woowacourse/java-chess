package controller;

import static command.PlayCommand.END;
import static command.PlayCommand.SAVE;
import static command.PlayCommand.STATUS;

import command.PlayCommandParser;
import command.StartCommand;
import database.dao.ChessGameDao;
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
            nextStep = executeCommand(readMoveCommand());
        } while (nextStep);
    }

    private boolean executeCommand(final PlayCommandParser command) {
        if (END.equals(command.getPlayCommand())) {
            return false;
        }
        if (STATUS.equals(command.getPlayCommand())) {
            OutputView.printGameScoreStatus(chessGame);
            return true;
        }
        if (SAVE.equals(command.getPlayCommand())) {
            savaStatus();
            return true;
        }
        boolean isEndedGame = movePieceWithHandling(command);
        OutputView.printBoard(chessGame.getPieces());
        return !isEndedGame;
    }

    private void savaStatus() {
        ChessGameDao chessGameDao = new ChessGameDao();
        String boardId = InputView.readBoardName();
        try {
            chessGameDao.saveBoard(chessGame, boardId);
        } catch (RuntimeException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private boolean movePieceWithHandling(final PlayCommandParser command) {
        try {
            return chessGame.movePiece(command.getSource(), command.getDestination());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return false;
        }
    }

    private PlayCommandParser readMoveCommand() {
        List<String> commands;
        while (validateInputMoveCommand(commands = InputView.readPlayGameOption())) {
        }
        return PlayCommandParser.parse(commands);
    }

    private boolean validateInputMoveCommand(List<String> commands) {
        try {
            PlayCommandParser.parse(commands);
            return false;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return true;
        }
    }
}
