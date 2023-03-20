package chess.controller;

import chess.domain.*;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.function.Function;
import java.util.function.Supplier;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printInitialMessage();
        Command command = repeatUntilNoException(this::startGame);
        ChessBoard chessBoard = new ChessBoardFactory().generate();
        while (command.isPlaying()) {
            outputView.printChessBoard(ChessBoardDto.of(chessBoard.getPieces()));
            command = repeatUntilNoException(this::playTurn, chessBoard);
        }
    }

    private Command startGame() {
        CommandDto commandDto = inputView.readCommand();
        return Command.startGame(commandDto.getCommand());
    }

    private Command playTurn(final ChessBoard chessBoard) {
        CommandDto commandDto = inputView.readCommand();
        Command command = Command.changeStatus(commandDto.getCommand());
        if (command.isPlaying()) {
            final Square from = toSquare(commandDto.getSourceRank(), commandDto.getSourceFile());
            final Square to = toSquare(commandDto.getDestinationRank(), commandDto.getDestinationFile());
            if (!chessBoard.canMove(from, to)) {
                outputView.printInvalidMoveMessage();
            }
        }
        return command;
    }

    private Square toSquare(final String rankSymbol, final String fileSymbol) {
        final Rank rank = Rank.from(rankSymbol);
        final File file = File.from(fileSymbol);
        return Square.of(rank, file);
    }

    private <T> T repeatUntilNoException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private <T, R> R repeatUntilNoException(Function<T, R> function, T arg) {
        while (true) {
            try {
                return function.apply(arg);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
