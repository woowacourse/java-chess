package chess.web;

import static spark.Spark.*;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");
        WebController controller = new WebController();
        controller.run();
    }
}
