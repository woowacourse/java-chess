package chess;

import java.sql.SQLException;
import java.util.Scanner;

import chess.controller.ChessGameController;
import chess.dao.JDBCConnection;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        try (
                JDBCConnection jdbcConnection = new JDBCConnection();
                Scanner scanner = new Scanner(System.in)
        ) {
            InputView inputView = new InputView(scanner);
            OutputView outputView = new OutputView();
            new ChessGameController(inputView, outputView, jdbcConnection).start();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
