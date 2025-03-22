package chess.Controller;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Board board = new Board();

        while (true) {
            try {
                outputView.printChessBoard(board);
                outputView.printTurn(board.getTurn());

                List<String> pieceInput = inputView.inputMovePiece();
                List<String> positionInput = inputView.inputMovePosition();
                board.movePiece(pieceInput, positionInput);

                board.changeTurn();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
