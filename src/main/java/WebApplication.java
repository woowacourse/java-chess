import chess.web.ChessController;
import org.apache.log4j.BasicConfigurator;

import static spark.Spark.*;

public class WebApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("templates");
        BasicConfigurator.configure();
        ChessController chessController = new ChessController();
        chessController.run();
    }
}
