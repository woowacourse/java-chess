package chess;

import chess.database.ChessGameDao;
import chess.database.JdbcConnector;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameApplication {

    public static void main(String[] args) {
        int startCommand = readStartCommand();
        ChessController chessController = new ChessController(startCommand);
        chessController.printBoard();
        chessController.run();
    }

    private static int readStartCommand() {
        try {
            ChessGameDao chessGameDao = new ChessGameDao(new JdbcConnector());
            return InputView.printGameStartMessage(chessGameDao.findRemainGames());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return readStartCommand();
        }
    }
}
