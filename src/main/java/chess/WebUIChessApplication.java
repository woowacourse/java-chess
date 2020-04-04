package chess;

import chess.repository.InMemoryChessRepository;
import chess.repository.InMemoryMovementRepository;
import chess.service.ChessService;
import chess.web.ChessController;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        final ChessService chessService = new ChessService(new InMemoryChessRepository(), new InMemoryMovementRepository());
        final ChessController chessController = new ChessController(chessService);
        chessController.run();
    }

}
