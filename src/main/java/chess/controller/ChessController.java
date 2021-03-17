package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Scanner;

public class ChessController {
    private static final InputView INPUT = new InputView(new Scanner(System.in));

    public void run() {
        if (isStart(INPUT.inputStart())) {
            ChessBoard chessBoard = initializeChessBoard();
            OutputView.showChessBoard(chessBoard.getBoard());
        }
        if (isEnd(INPUT.inputEnd())) {
            return;
        }
    }

    private ChessBoard initializeChessBoard() {
        return new ChessBoard(ChessBoardFactory.initializeBoard());
    }

    private boolean isStart(String userInput) {
        return "start".equals(userInput);
    }

    private boolean isEnd(String userInput) {
        return "end".equals(userInput);
    }
}
