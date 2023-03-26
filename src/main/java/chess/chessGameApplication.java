package chess;

import chess.view.InputView;
import chess.view.OutputView;

public class chessGameApplication {

    public static void main(String[] args) {
        int newStartCommand = readStartCommand();
        System.out.println("newStartCommand = " + newStartCommand);
        ChessBoard chessBoard = ChessBoard.generateChessBoard(newStartCommand);
        OutputView.printChessBoard(chessBoard.getChessBoard());
        ChessGame chessGame = new ChessGame(chessBoard, newStartCommand);
        ChessController chessController = new ChessController(chessBoard, chessGame);
//        chessController.startPhase();
        chessController.commandPhase();
    }

    public static int readStartCommand() {
        try {
            return InputView.printGameStartMessage();
//            OutputView.printChessBoard(chessBoard.getChessBoard());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return readStartCommand();
        }
    }
}
