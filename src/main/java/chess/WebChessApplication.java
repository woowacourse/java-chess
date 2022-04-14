package chess;

import static spark.Spark.*;

public class WebChessApplication {

    public static void main(String[] args) {
        port(8081);
        staticFiles.location("templates");

        WebChessController webChessController = new WebChessController();
        webChessController.run();
    }
}
