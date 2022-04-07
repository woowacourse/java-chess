package web;

import web.controller.ChessWebController;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebApplication {

    public static void main(String[] args) {
        port(8082);
        staticFileLocation("/static");
        ChessWebController controller = new ChessWebController();
        controller.run();
    }
}
