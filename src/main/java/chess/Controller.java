package chess;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.command.MoveCommand;
import chess.view.command.StartCommand;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        StartCommand startCommand = handleException(inputView::readWannaStart);
        if (startCommand.isStart()) {
            Board board = new Board();
            outputView.printBoard(board);
            handleException(this::move, board);
        }
    }

    private void move(Board board) {
        MoveCommand moveCommand = inputView.readMoveCommand();
        while (!moveCommand.isEnd()) {
            Coordinate source = createCoordinate(moveCommand.source());
            board.move(source, createCoordinate(moveCommand.target()));
            outputView.printBoard(board);
            moveCommand = inputView.readMoveCommand();
        }
    }

    private Coordinate createCoordinate(String input) {
        List<String> coordinateInput = Arrays.asList(input.split(""));
        if (coordinateInput.size() != 2) {
            throw new IllegalArgumentException("올바른 형식의 좌표를 입력해주세요. ex) a2");
        }
        int rankValue = Integer.parseInt(coordinateInput.get(1));
        char fileValue = coordinateInput.get(0).charAt(0);

        return new Coordinate(rankValue, fileValue);
    }

    private <T> void handleException(Consumer<T> consumer, T target) {
        try {
            consumer.accept(target);
        } catch (Exception exception) {
            outputView.printExceptionMessage(exception);
            handleException(consumer, target);
        }
    }

    private <T> T handleException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception exception) {
            outputView.printExceptionMessage(exception);
            return handleException(supplier);
        }
    }
}
