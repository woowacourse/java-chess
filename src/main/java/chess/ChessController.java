package chess;

import chess.view.OutputView;

import java.util.StringTokenizer;

import static chess.view.InputView.*;

public class ChessController {

    public void start() {
        ChessGame chessGame = new ChessGame();
        String command = new StringTokenizer(startGame()).nextToken();

        if (command.equals("start")) {
            chessGame.start();
            OutputView.printBoard(chessGame.getChessBoard());
        }
        if (command.equals("end")) {
            OutputView.printBoard(chessGame.getChessBoard());
        }
    }
}
