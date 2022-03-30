package chess.controller;

import static chess.vo.Command.*;

import chess.dto.Request;
import chess.model.Board;
import chess.model.TurnDecider;
import chess.model.boardinitializer.defaultInitializer;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        Board board = new Board(new TurnDecider(), new defaultInitializer());

        if (InputView.inputCommandInStart() == END) {
            return;
        }

        OutputView.printChessGameBoard(board.getValues());
        playChess(board);
    }

    private void playChess(Board board) {
        while (!board.isFinished()) {
            Request request = InputView.inputCommandInGaming();
            if (request.getCommand() == END) {
                break;
            }

            if (request.getCommand() == STATUS) {
                OutputView.printScore(board.calculateScore());
                continue;
            }

            board.move(request.getSource(), request.getTarget());
            OutputView.printChessGameBoard(board.getValues());
        }
        OutputView.printScore(board.calculateScore());
    }
}
