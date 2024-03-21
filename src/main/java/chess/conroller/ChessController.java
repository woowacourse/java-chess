package chess.conroller;

import chess.domain.ChessBoard;
import chess.domain.StartCommand;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void runChess() {
        StartCommand startCommand = StartCommand.from(inputView.readStartCommand());

        final ChessBoard chessBoard = ChessBoard.init();
        if (startCommand.equals(StartCommand.START)) {
            outputView.printChessBoard(chessBoard.getPieces());
        }


        List<String> positions = inputView.readMoveCommand();
        chessBoard.move(new Position(positions.get(0)), new Position(positions.get(1)));
        outputView.printChessBoard(chessBoard.getPieces());

        if (!startCommand.equals(StartCommand.END)) {
            startCommand = StartCommand.from(inputView.readEnd());
        }
    }
}
