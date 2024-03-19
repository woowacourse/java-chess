package chess.conroller;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void runChess() {
        Command command = Command.from(inputView.readCommand());

        if (command.equals(Command.START)) {
            final ChessBoard chessBoard = ChessBoard.init();
            outputView.printChessBoard(chessBoard.getBoard());
        }

        if (!command.equals(Command.END)) {
            command = Command.from(inputView.readEnd());
        }
    }
}
