package chess.controller;

import static chess.Command.END;

import chess.Board;
import chess.Command;
import chess.dto.MoveRequest;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();

        Board board = new Board();
        Command beginCommand = InputView.inputCommandInStart();

        if (beginCommand == END) {
            return;
        }

        OutputView.printChessGameBoard(board.getValues());
        while (true) {
            MoveRequest moveRequest = InputView.inputCommandInGaming();
            if (moveRequest.getCommand() == END) {
                break;
            }
            board.move(moveRequest.getSource(), moveRequest.getTarget());
            OutputView.printChessGameBoard(board.getValues());
        }
    }
}
