package controller;

import domain.GameCommand;
import domain.game.Board;
import domain.game.BoardInitializer;
import domain.game.Turn;
import dto.BoardDto;
import dto.RequestDto;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        GameCommand command = readUserInput(inputView::inputGameStart);
        if (command.isStart()) {
            startGame();
        }
    }

    private <T> T readUserInput(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void startGame() {
        Board board = BoardInitializer.init();
        printStatus(board);

        Turn turn = new Turn();
        RequestDto requestDto = readUserInput(inputView::inputGameCommand);
        while (requestDto.command().isContinuable()) {
            doTurn(board, turn, requestDto);
            printStatus(board);
            requestDto = readUserInput(inputView::inputGameCommand);
        }
    }

    private void doTurn(Board board, Turn turn, RequestDto requestDto) {
        try {
            board.movePiece(turn.current(), requestDto.source(), requestDto.destination());
            turn.next();
        } catch (IllegalArgumentException e) {
            System.out.println("[오류] " + e.getMessage());
        }
    }

    private void printStatus(Board board) {
        BoardDto boardDto = BoardDto.from(board);
        outputView.printBoard(boardDto);
    }
}
