package chess;

import chess.repository.ConnectionProperties;
import chess.repository.InMemoryChessRepository;
import chess.repository.InMemoryMovementRepository;
import chess.repository.MariaChessRepository;
import chess.repository.MariaMovementRepository;
import chess.service.ChessService;
import chess.web.ChessController;

import static chess.repository.ChessConnection.getConnection;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        ChessService chessService = new ChessService(new InMemoryChessRepository(), new InMemoryMovementRepository());

        ConnectionProperties connectionProperties = new ConnectionProperties();
        if (getConnection(connectionProperties) != null) {
            chessService = new ChessService(new MariaChessRepository(connectionProperties), new MariaMovementRepository(connectionProperties));
        }

        final ChessController chessController = new ChessController(chessService);
        chessController.run();
    }

}
