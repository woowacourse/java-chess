package chess.controller;

import static chess.Command.*;

import chess.Board;
import chess.Command;
import chess.dto.Request;
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
            Request request = InputView.inputCommandInGaming();
            if (request.getCommand() == END) {
                break;
            }

            if (request.getCommand() == Command.STATUS) {
                System.out.println(board.calculateScore());
                continue;
            }

            boolean isFinished = board.move(request.getSource(), request.getTarget());
            OutputView.printChessGameBoard(board.getValues());

            if (isFinished) {
                break;
            }
        }
    }
}
