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
        GameStatus gameStatus = repeatUntilNoException(this::startGame);
        ChessBoard chessBoard = new ChessBoardFactory().generate();
        while (gameStatus.isPlaying()) {
            outputView.printChessBoard(ChessBoardDto.of(chessBoard.getPieces()));
            gameStatus = repeatUntilNoException(this::playTurn, chessBoard);
        }
    }

    private GameStatus startGame() {
        CommandDto commandDto = inputView.readCommand();
        GameStatus gamestatus = GameStatus.startGame(commandDto.getCommand());
        return gamestatus;
    }

    private GameStatus playTurn(final ChessBoard chessBoard) {
        CommandDto commandDto = inputView.readCommand();
        GameStatus gamestatus = GameStatus.changeStatus(commandDto.getCommand());
        if (gamestatus.isPlaying()) {
            final Square from = toSquare(commandDto.getSourceRank(), commandDto.getSourceFile());
            final Square to = toSquare(commandDto.getDestinationRank(), commandDto.getDestinationFile());
            if (!chessBoard.move(from, to)) {
                outputView.printInvalidMoveMessage();
            }
        }
        return gamestatus;
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
