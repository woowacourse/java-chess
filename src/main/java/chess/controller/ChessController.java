package chess.controller;

import static chess.Command.*;

import chess.Board;
import chess.Command;
import chess.dto.MoveRequest;
import chess.dto.view.InputView;
import chess.dto.view.OutputView;
import chess.turndecider.AlternatingTurnDecider;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();

        Board board = new Board(new AlternatingTurnDecider());
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

            if (moveRequest.getCommand() == Command.STATUS) {
                System.out.println(board.calculateScore());
                continue;
            }

            boolean isFinished = board.move(moveRequest.getSource(), moveRequest.getTarget());
            OutputView.printChessGameBoard(board.getValues());

            if (isFinished) {
                break;
            }
        }
    }
}
