package chess;

import chess.controller.ChessController;
import chess.database.DataSource;
import chess.database.dao.ChessGameDao;
import chess.database.dao.MySQLChessGameDao;
import chess.view.InputView;
import chess.view.OutputView;
import java.sql.Connection;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        ChessGameDao chessGameDao = new MySQLChessGameDao();
        Connection connection = DataSource.getConnection();
        ChessController chessController = new ChessController(inputView, outputView, chessGameDao,
            connection);
        chessController.run();
    }

}
