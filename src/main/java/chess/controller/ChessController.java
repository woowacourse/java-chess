package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.chesspieces.ChessPiece;
import chess.domain.position.Positions;
import chess.views.InputView;
import chess.views.OutputView;

public class ChessController {
    public void play() {
        start();
        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard.getChessBoard());
        while (true) {
            String input = InputView.inputCommand();
            if (Command.of(input.split(" ")[0]).equals(Command.MOVE)) {
                // do something
                ChessPiece source = chessBoard.getChessBoard().get(input.split(" ")[1]);
                source.movable(Positions.of(input.split(" ")[1]),
                        Positions.of(input.split(" ")[2]));
            }
            if (Command.of(input).equals(Command.END)) {
                return;
            }
        }
    }

    public void start() {
        do {
            OutputView.printInitialGuide();
        } while (!Command.of(InputView.inputCommand()).equals(Command.START));
    }
}
