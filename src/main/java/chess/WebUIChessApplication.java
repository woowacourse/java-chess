package chess;

import chess.dao.ConnectionProperties;
import chess.dao.InMemoryChessDAO;
import chess.dao.InMemoryMovementDAO;
import chess.dao.MariaChessDAO;
import chess.dao.MariaMovementDAO;
import chess.service.ChessService;
import chess.web.ChessController;

import static chess.dao.ChessConnection.getConnection;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        ChessService chessService = new ChessService(new InMemoryChessDAO(), new InMemoryMovementDAO());

        ConnectionProperties connectionProperties = new ConnectionProperties();
        if (getConnection(connectionProperties) != null) {
            chessService = new ChessService(new MariaChessDAO(connectionProperties), new MariaMovementDAO(connectionProperties));
        }

        final ChessController chessController = new ChessController(chessService);
        chessController.run();
    }

}
