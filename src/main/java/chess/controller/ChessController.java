package chess.controller;

import chess.board.Board;
import chess.board.dto.BoardDto;
import chess.piece.Pieces;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.function.Supplier;

public class ChessController {
    private final InputView inputView;

    public ChessController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        OutputView.printGameStartMessage();
        OutputView.printGameCommandInputMessage();
        while (!inputGameCommand().equals("end")) {
            OutputView.printBoard(new BoardDto(new Board(new Pieces())));
        }
    }

    private String inputGameCommand() {
        return repeatForValidInput(inputView::inputGameCommand);
    }

    private <T> T repeatForValidInput(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return repeatForValidInput(supplier);
        }
    }
}
