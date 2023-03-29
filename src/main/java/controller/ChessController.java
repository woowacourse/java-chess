package controller;

import controller.command.play.PlayAction;
import controller.command.play.PlayCommandType;
import controller.command.start.StartAction;
import controller.command.start.StartCommandType;
import domain.ChessGame;
import java.util.List;
import java.util.Objects;
import util.ParameterParser;
import view.InputView;
import view.OutputView;

public final class ChessController {

    private static final int COMMAND_HEAD_INDEX = 0;

    public ChessController() {
    }

    public void run() {
        ChessGame chessGame = readStartCommandAndInitGame();
        if (Objects.nonNull(chessGame)) {
            OutputView.printBoard(chessGame.getPieces());
            play(chessGame);
        }
        OutputView.printEndedGameMessage();
    }

    private ChessGame readStartCommandAndInitGame() {
        ChessGame chessGame = null;
        do {
            chessGame = initChessGame(InputView.readStartGameCommand());
        } while (Objects.isNull(chessGame));
        return chessGame;
    }

    private ChessGame initChessGame(List<String> command) {
        try {
            StartAction startAction = StartCommandType.from(command.get(COMMAND_HEAD_INDEX));
            ChessGame chessGame = startAction.init(ParameterParser.parsingParameterFromCommand(command));
            return chessGame;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return null;
        }
    }

    private void play(final ChessGame chessGame) {
        boolean stillPlaying;
        do {
            stillPlaying = readPlayCommandAndExecute(InputView.readPlayGameOption(), chessGame);
        } while (stillPlaying);
    }

    private boolean readPlayCommandAndExecute(final List<String> command, final ChessGame chessGame) {
        try {
            PlayAction playAction = PlayCommandType.from(command.get(COMMAND_HEAD_INDEX));
            return playAction.execute(chessGame, ParameterParser.parsingParameterFromCommand(command));
        } catch (RuntimeException e) {
            OutputView.printError(e.getMessage());
            return true;
        }
    }
}
