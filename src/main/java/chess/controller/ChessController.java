package chess.controller;

import chess.domain.Board;
import chess.domain.order.Order;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;


public class ChessController {

    public void run() {
        Board board = Board.create(new HashMap<>());

        startGame(board);
        playGame(board);
    }

    private void startGame(final Board board) {
        try {
            Order.ofStart(InputView.askStart());
            OutputView.printBoard(board);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            startGame(board);
        }
    }

    private void playGame(final Board board) {
        try {
            Order order = Order.ofMoveOrEnd(InputView.askNext());

            if (order.isEnd()) {
                return;
            }
            if (order.isMove()) {
                board.move(order.getSource(), order.getTarget());
                OutputView.printBoard(board);
                playGame(board);
            }
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            playGame(board);
        }
    }
}
