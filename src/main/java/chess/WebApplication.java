package chess;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.controller.WebChessController;
import chess.dao.DataSource;
import chess.dao.DataSourceImpl;
import chess.dao.SquareDaoImpl;
import chess.dao.StateDaoImpl;
import chess.repository.GameRepositoryImpl;
import chess.service.GameService;

public class WebApplication {

    public static void main(String[] args) {
        port(8765);
        staticFileLocation("/static");
        DataSource dataSource = initConnectionInfo();
        GameService gameService = new GameService(
                new GameRepositoryImpl(new SquareDaoImpl(dataSource), new StateDaoImpl(dataSource)));
        WebChessController webChessController = new WebChessController(gameService);
        webChessController.run();
    }

    private static DataSource initConnectionInfo() {
        final String url = "jdbc:mysql://localhost:3306/chess";
        final String user = "user";
        final String password = "password";
        return new DataSourceImpl(url, user, password);
    }
}
