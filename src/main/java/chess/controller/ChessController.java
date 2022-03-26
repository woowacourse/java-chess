package chess.controller;

import static chess.Command.*;

import chess.Board;
import chess.defaultInitializer;
import chess.dto.Request;
import chess.turndecider.AlternatingTurnDecider;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        Board board = new Board(new AlternatingTurnDecider(), new defaultInitializer());

        if (InputView.inputCommandInStart() == END) {
            return;
        }

        OutputView.printChessGameBoard(board.getValues());
        playChess(board);
    }

    private void playChess(Board board) {
        Request request = InputView.inputCommandInGaming();
        if (request.getCommand() == END) {
            return;
        }

        if (request.getCommand() == STATUS) {
            OutputView.printScore(board.calculateScore());
            playChess(board);
        }

        boolean isFinished = board.move(request.getSource(), request.getTarget());
        OutputView.printChessGameBoard(board.getValues());

        if (isFinished) {
            OutputView.printScore(board.calculateScore());
            return;
        }

        playChess(board);
    }
}
