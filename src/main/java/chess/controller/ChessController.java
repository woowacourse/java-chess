package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard();

        while (true) {
            outputView.printChessBoard(chessBoard);
            InputProcessor.processUntilSuccess(() -> {
                List<Position> originAndDestination = inputView.getMoveInput();
                chessBoard.movePiece(originAndDestination.get(0), originAndDestination.get(1));
            }, OutputView::printErrorMessage);


//            List<Color> colors = Color.getGameColors();
//            for (Color color : colors) {
//                outputView.printTurnMessage(color);
//            }
        }
    }
}
