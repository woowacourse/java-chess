package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.StartCommand;
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
        while (isNotEndCommand(positions)) {
            chessBoard.move(positions);
            outputView.printChessBoard(chessBoard.getPieces());
            positions = inputView.readMoveCommand();
        }
    }

    private boolean isNotEndCommand(final List<String> positions) {
        return !positions.isEmpty();
    }
}
