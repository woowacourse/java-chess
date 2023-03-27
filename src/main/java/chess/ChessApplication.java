package chess;

import chess.controller.ChessController;
import chess.dao.ChessMovementDao;
import chess.dao.ConnectionStrategy;
import chess.dao.JdbcChessMovementDao;
import chess.dao.MySqlConnectionStrategy;
import chess.model.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Scanner;

public class ChessApplication {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final InputView inputView = new InputView(scanner);
        final OutputView outputView = new OutputView();
        final ChessController chessController = new ChessController(inputView, outputView);
        final ChessGame chessGame = new ChessGame();
        final ConnectionStrategy connectionStrategy = new MySqlConnectionStrategy();
        final ChessMovementDao chessMovementDao = new JdbcChessMovementDao(connectionStrategy);

        chessController.start(chessGame, chessMovementDao);
    }
}
