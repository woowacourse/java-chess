package controller;

import domain.ChessGame;
import domain.piece.Color;
import dto.DtoMapper;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import view.InputView;
import view.OutputView;

public class ChessController {
    public void start() {
        final ChessGame chessGame = new ChessGame();
        chessGame.recover();
        OutputView.printGameStartMessage();
        retryUntilNoException(this::play, chessGame);
        wrapUp(chessGame);
    }

    private void wrapUp(final ChessGame chessGame) {
        if (chessGame.isKingDead()) {
            chessGame.reset();
            OutputView.printChessResult(DtoMapper.generateGameResultResponse(chessGame.calculateWhiteScore(),
                    chessGame.calculateBlackScore(), chessGame.isKingDeadOf(Color.WHITE),
                    chessGame.isKingDeadOf(Color.BLACK)));
            OutputView.printGameEndMessage();
        }
    }

    private void play(final ChessGame chessGame) {
        while (chessGame.isRunning()) {
            final String command = InputView.inputCommand();
            runCommand(chessGame, command);
        }
    }

    private void runCommand(final ChessGame chessGame, final String command) {
        if (isCommandStatus(command)) {
            OutputView.printChessResult(DtoMapper.generateGameResultResponse(chessGame.calculateWhiteScore(),
                    chessGame.calculateBlackScore(), chessGame.isKingDeadOf(Color.WHITE),
                    chessGame.isKingDeadOf(Color.WHITE)));
            return;
        }
        chessGame.execute(command);
        printChessBoardIfRunning(chessGame);
    }

    private boolean isCommandStatus(final String command) {
        return command.equals("status");
    }

    private void printChessBoardIfRunning(final ChessGame chessGame) {
        if (chessGame.isRunning()) {
            OutputView.printChessBoard(DtoMapper.generateBoardResponse(chessGame.getSquares()));
        }
    }

    private <T> T retryUntilNoException(final Consumer<ChessGame> consumer, final ChessGame chessGame) {
        try {
            consumer.accept(chessGame);
        } catch (final IllegalArgumentException | UnsupportedOperationException | NoSuchElementException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return retryUntilNoException(consumer, chessGame);
        }
        return null;
    }
}
