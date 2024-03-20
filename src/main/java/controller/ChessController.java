package controller;

import domain.Board;
import domain.BoardInitializer;
import domain.GameCommand;
import dto.BoardDto;
import dto.RequestDto;
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
        RequestDto requestDto = inputView.inputGameCommand();

        if (requestDto.gameCommand() == GameCommand.END) {
            return;
        }

        startGame();
    }

    private void startGame() {
        Board board = BoardInitializer.init();

        BoardDto boardDto = BoardDto.from(board);

        outputView.printBoard(boardDto);
    }
}
