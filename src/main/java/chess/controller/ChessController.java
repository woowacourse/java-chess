package chess.controller;

import chess.domain.game.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.GameCommand;
import chess.domain.game.MoveCommand;
import chess.view.GameCommandView;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionConverter;

import java.util.List;

import static chess.view.PositionConverter.convertToSourcePosition;
import static chess.view.PositionConverter.convertToTargetPosition;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(MoveCommand moveCommand) {
        outputView.printStartMessage();
        ChessGame chessGame = new ChessGame(new Board());
        while (chessGame.isNotTerminated()) {
            playChessGameWithExceptionHandling(chessGame, moveCommand);
        }
        outputView.printInputStatusMessage();
        playChessGameWithExceptionHandling(chessGame, moveCommand);
        save(moveCommand);
    }

    private void playChessGameWithExceptionHandling(ChessGame chessGame, MoveCommand moveCommand) {
        try {
            String command = inputView.readCommand();
            playChessGame(chessGame, command, moveCommand);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
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
        chessGame.inputGameCommand(GameCommand.START);
        outputView.printBoard(chessGame.getBoard());
    }

    private void stateChessGame(ChessGame chessGame) {
        chessGame.inputGameCommand(GameCommand.STATUS);
        double blackScore = chessGame.calculateBlackScore();
        double whiteScore = chessGame.calculateWhiteScore();
        outputView.printStatus(blackScore, whiteScore);
    }

    private void endChessGame(ChessGame chessGame) {
        chessGame.inputGameCommand(GameCommand.END);
    }

    private void progressChessGame(ChessGame chessGame, String command, MoveCommand moveCommand) {
        if (GameCommandView.isValidCommandWithoutMove(command)) {
            return;
        }
        chessGame.inputGameCommand(GameCommand.MOVE);
        chessGame.progress(convertToSourcePosition(command), convertToTargetPosition(command));
        moveCommand.addMoveCommand(command);
        outputView.printBoard(chessGame.getBoard());
    }

    private void save(MoveCommand moveCommand) {
        try {
            outputView.printWantSaveGame();
            readSaveAnswer(moveCommand);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            save(moveCommand);
        }
    }

    private void readSaveAnswer(MoveCommand moveCommand) {
        if (inputView.doNotSave()) {
            moveCommand.clear();
        }
    }

    public void reStart(MoveCommand moveCommand) {
        ChessGame chessGame = new ChessGame(new Board());
        chessGame.inputGameCommand(GameCommand.START);
        interpretCommands(chessGame, moveCommand);
        outputView.printBoard(chessGame.getBoard());
        while (chessGame.isNotTerminated()) {
            playChessGameWithExceptionHandling(chessGame, moveCommand);
        }
        outputView.printInputStatusMessage();
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
