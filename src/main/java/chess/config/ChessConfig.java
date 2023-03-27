package chess.config;

import chess.controller.ChessController;
import chess.dao.ChessMovementDao;
import chess.dao.ConnectionStrategy;
import chess.dao.JdbcChessMovementDao;
import chess.dao.MySqlConnectionStrategy;
import chess.model.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Scanner;

public class ChessConfig {

    public ChessController chessController() {
        return new ChessController(inputView(), outputView());
    }

    private InputView inputView() {
        final Scanner scanner = new Scanner(System.in);

        return new InputView(scanner);
    }

    private OutputView outputView() {
        return new OutputView();
    }

    public ChessGameService chessGameService() {
        final ChessGame chessGame = new ChessGame();

        return new ChessGameService(chessGame, chessMovementDao());
    }

    private ChessMovementDao chessMovementDao() {
        return new JdbcChessMovementDao(connectionStrategy());
    }

    private ConnectionStrategy connectionStrategy() {
        return new MySqlConnectionStrategy();
    }
}
