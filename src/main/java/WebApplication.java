import chess.web.ChessController;
import org.apache.log4j.BasicConfigurator;

import static spark.Spark.*;

public class WebApplication {
    public static String STATUS = "dev";

    public static void main(String[] args) {
        port(8080);

        if (STATUS.equals("dev")) {
            String projectDirectory = System.getProperty("user.dir");
            String staticDirectory = "/src/main/resources/templates";
            externalStaticFileLocation(projectDirectory + staticDirectory);
        } else {
            staticFileLocation("/templates");
        }
        staticFileLocation("templates");
        BasicConfigurator.configure();
        ChessController chessController = new ChessController();
        chessController.run();
    }
}
