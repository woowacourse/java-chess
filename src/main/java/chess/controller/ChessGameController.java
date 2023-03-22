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
        ChessBoard chessBoard = ChessBoardFactory.generate();
        while (command.isPlaying()) {
            outputView.printChessBoard(ChessBoardDto.of(chessBoard.getPieces()));
            command = repeatUntilNoException(this::playTurn, chessBoard);
        }
    }

    private Command startGame() {
        CommandDto commandDto = inputView.readCommand();
        Command command = Command.from(commandDto.getCommand());
        command.validateCommandInStart();
        return command;
    }

    private Command playTurn(final ChessBoard chessBoard) {
        CommandDto commandDto = inputView.readCommand();
        Command command = Command.from(commandDto.getCommand());
        command.validateCommandInPlaying();
        if (command.isPlaying()) {
            updateChessBoard(chessBoard, commandDto);
        }
        return command;
    }

    private void updateChessBoard(final ChessBoard chessBoard, final CommandDto commandDto) {
        final Square from = Square.of(Rank.from(commandDto.getSourceRank()), File.from(commandDto.getSourceFile()));
        final Square to = Square.of(Rank.from(commandDto.getDestinationRank()), File.from(commandDto.getDestinationFile()));
        validateSameSquare(from, to);
        if (!chessBoard.canMove(from, to)) {
            outputView.printInvalidMoveMessage();
        }
    }

    private void validateSameSquare(final Square from, final Square to) {
        if (from == to) {
            throw new IllegalArgumentException("같은 지점이 들어왔습니다.");
        }
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
