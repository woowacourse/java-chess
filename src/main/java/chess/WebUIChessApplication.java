package chess;

import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("templates");

        WebChessController webChessController = new WebChessController();
        webChessController.run();
    }
}
