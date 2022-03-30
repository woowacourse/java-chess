package web;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class WebApplication {

    public static void main(String[] args) {
        ChessGameService service = new ChessGameService();
        ChessGameController controller = new ChessGameController(service);

        port(8080);
        staticFileLocation("/static");

        get("/", controller::index);
        post("/move", controller::move);
    }
}
