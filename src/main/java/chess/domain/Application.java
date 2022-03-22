package chess.domain;

import chess.domain.piece.Pieces;
import chess.domain.view.InputView;
import chess.domain.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        outputView.printGameStartMessage();

        String startOrEndInput = inputView.getStartOrEndInput(outputView);
        if (startOrEndInput.equals("start")) {
            Board board = new Board(new Pieces());
        }

        inputView.terminate();
    }
}
