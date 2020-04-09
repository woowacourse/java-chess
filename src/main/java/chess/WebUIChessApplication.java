package chess;

import chess.dao.ConnectionProperties;
import chess.dao.InMemoryChessDao;
import chess.dao.InMemoryMovementDao;
import chess.dao.MariaChessDao;
import chess.dao.MariaMovementDao;
import chess.service.ChessService;
import chess.web.ChessController;

import static chess.dao.ChessConnection.getConnection;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        ChessService chessService = new ChessService(new InMemoryChessDao(), new InMemoryMovementDao());

        ConnectionProperties connectionProperties = new ConnectionProperties();
        if (getConnection(connectionProperties) != null) {
            chessService = new ChessService(new MariaChessDao(connectionProperties), new MariaMovementDao(connectionProperties));
        }

        final ChessController chessController = new ChessController(chessService);
        chessController.run();
    }

}
