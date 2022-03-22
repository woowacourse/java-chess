package chess;

import chess.domain.ChessBoard;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printGameGuide();
        run();
    }

    private static void run() {
        Command command = InputView.requestGameCommand();
        if (command == Command.END) {
            return;
        }
        ChessBoard chessBoard = ChessBoard.createNewChessBoard();
        OutputView.printChessBoard(chessBoard.getPieces());
        run();
    }
}
