package chess.controller;

import chess.domain.game.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.MoveCommand;
import chess.view.ChessInputView;
import chess.view.ChessOutputView;
import chess.view.GameCommandView;
import chess.view.PositionConverter;

import java.util.List;

import static chess.view.PositionConverter.convertToSourcePosition;
import static chess.view.PositionConverter.convertToTargetPosition;

public class ChessController {

    private static ChessController chessController;

    private final ChessInputView chessInputView;
    private final ChessOutputView chessOutputView;

    private ChessController(ChessInputView chessInputView, ChessOutputView chessOutputView) {
        this.chessInputView = chessInputView;
        this.chessOutputView = chessOutputView;
    }

    public static ChessController getInstance() {
        if (chessController == null) {
            chessController = new ChessController(new ChessInputView(), new ChessOutputView());
        }
        return chessController;
    }

    public void run(MoveCommand moveCommand) {
        chessOutputView.printStartMessage();
        ChessGame chessGame = new ChessGame(new Board());
        while (chessGame.isNotTerminated()) {
            playChessGameWithExceptionHandling(chessGame, moveCommand);
        }
        chessOutputView.printInputStatusMessage();
        playChessGameWithExceptionHandling(chessGame, moveCommand);
        save(moveCommand);
    }

    private void playChessGameWithExceptionHandling(ChessGame chessGame, MoveCommand moveCommand) {
        try {
            String command = chessInputView.readCommand();
            playChessGame(chessGame, command, moveCommand);
        } catch (IllegalArgumentException e) {
            chessOutputView.printExceptionMessage(e);
            playChessGameWithExceptionHandling(chessGame, moveCommand);
        }
    }

    private void playChessGame(ChessGame chessGame, String command, MoveCommand moveCommand) {
        if (GameCommandView.isStartCommand(command)) {
            startChessGame(chessGame);
        }
        if (GameCommandView.isStatusCommand(command)) {
            stateChessGame(chessGame);
        }
        if (GameCommandView.isEndCommand(command)) {
            endChessGame(chessGame);
        }
        progressChessGame(chessGame, command, moveCommand);
    }

    private void startChessGame(ChessGame chessGame) {
        chessGame.start();
        chessOutputView.printBoard(chessGame.getBoard());
    }

    private void stateChessGame(ChessGame chessGame) {
        double blackScore = chessGame.calculateBlackScore();
        double whiteScore = chessGame.calculateWhiteScore();
        chessOutputView.printStatus(blackScore, whiteScore);
    }

    private void endChessGame(ChessGame chessGame) {
        chessGame.terminateGame();
    }

    private void progressChessGame(ChessGame chessGame, String command, MoveCommand moveCommand) {
        if (GameCommandView.isValidCommandWithoutMove(command)) {
            return;
        }
        chessGame.progress(convertToSourcePosition(command), convertToTargetPosition(command));
        moveCommand.addMoveCommand(command);
        chessOutputView.printBoard(chessGame.getBoard());
    }

    private void save(MoveCommand moveCommand) {
        try {
            chessOutputView.printWantSaveGame();
            readSaveAnswer(moveCommand);
        } catch (IllegalArgumentException e) {
            chessOutputView.printExceptionMessage(e);
            save(moveCommand);
        }
    }

    private void readSaveAnswer(MoveCommand moveCommand) {
        if (chessInputView.doNotSave()) {
            moveCommand.clear();
        }
    }

    public void reStart(MoveCommand moveCommand) {
        ChessGame chessGame = new ChessGame(new Board());
        chessGame.start();

        interpretCommands(chessGame, moveCommand);
        chessOutputView.printBoard(chessGame.getBoard());
        while (chessGame.isNotTerminated()) {
            playChessGameWithExceptionHandling(chessGame, moveCommand);
        }
        chessOutputView.printInputStatusMessage();
        playChessGameWithExceptionHandling(chessGame, moveCommand);
        save(moveCommand);
    }

    private void interpretCommands(ChessGame chessGame, MoveCommand moveCommandsInterpreter) {
        List<String> moveCommands = moveCommandsInterpreter.interpretMoveCommands();
        for (String moveCommand : moveCommands) {
            chessGame.progress(
                    PositionConverter.convertToSourcePosition(moveCommand),
                    PositionConverter.convertToTargetPosition(moveCommand)
            );
        }
    }
}
