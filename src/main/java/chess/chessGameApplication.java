package chess;

import chess.view.InputView;
import chess.view.OutputView;

public class chessGameApplication {

    public static void main(String[] args) {
        int newStartCommand = readStartCommand();
        ChessBoard chessBoard = ChessBoard.generateChessBoard(newStartCommand);
        OutputView.printChessBoard(chessBoard.getChessBoard());
        ChessGame chessGame = new ChessGame(chessBoard, newStartCommand);
        ChessController chessController = new ChessController(chessBoard, chessGame);
        chessController.commandPhase();
    }

    public static int readStartCommand() {
        try {
            return InputView.printGameStartMessage();
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return readStartCommand();
        }
    }
}
