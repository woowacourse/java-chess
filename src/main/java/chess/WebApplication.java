package chess;

import chess.controller.WebController;
import chess.service.ChessService;

import static spark.Spark.*;

public class WebApplication {
    public static String STATUS = "dev";

    public static void main(String[] args) {
        WebController webController = new WebController(new ChessService());

        if (STATUS.equals("dev")) {
            String projectDirectory = System.getProperty("user.dir");
            String staticDirectory = "/src/main/resources/static";
            externalStaticFileLocation(projectDirectory + staticDirectory);
        } else {
            staticFileLocation("/static");
        }

        port(8081);

        webController.run();
    }
}
