package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.dto.CommandDto;
import chess.dto.ScoreDto;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public final class Controller {
    private final CommandController commandController;
    private final ChessGame chessGame;

    public Controller(final CommandController commandController, final ChessGame chessGame) {
        this.commandController = commandController;
        this.chessGame = chessGame;
    }

    public void run() {
        OutputView.printStartMessage();

        setup();

        while (chessGame.isNotEnd()) {
            retryOnError(() -> {
                printStateInformation();
                CommandDto command = InputView.inputGameState();
                commandController.execute(command);
            });
        }
    }

    private void setup() {
        commandController.register(Command.START, command -> {
            chessGame.start();
            OutputView.printChessBoard(chessGame.getBoard());
        });
        commandController.register(Command.END, command -> {
            chessGame.end();
            OutputView.printEnd();
        });
        commandController.register(Command.STATUS, command -> {
            chessGame.identity();
            printStatus();
        });
        commandController.register(Command.MOVE, command -> {
            chessGame.move(command);
            OutputView.printChessBoard(chessGame.getBoard());
        });
    }

    private void printStatus() {
        if (chessGame.isGameEnd()) {
            OutputView.printScore(ScoreDto.of(chessGame.calculateScore(Color.BLACK), chessGame.calculateScore(Color.WHITE), chessGame.getColor()));
        }
        if (chessGame.isNotGameEnd()) {
            OutputView.printScore(ScoreDto.of(chessGame.calculateScore(Color.BLACK), chessGame.calculateScore(Color.WHITE)));
        }
    }

    private void printStateInformation() {
        if (chessGame.isRunning()) {
            OutputView.printColor(chessGame.getColor());
        }
        if (chessGame.isGameEnd()) {
            OutputView.printGameEnd();
        }
    }

    private void retryOnError(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }
}
