package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public static void run() {
        String gameState = InputView.inputGameState();

        if ("start".equalsIgnoreCase(gameState)) {
            ChessBoard chessBoard = new ChessBoard();
            OutputView.printChessBoard(chessBoard);
        }
        System.out.println("\n게임을 종료 합니다");
    }
}
