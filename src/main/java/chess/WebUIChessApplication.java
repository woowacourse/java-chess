package chess;

import static spark.Spark.staticFiles;

import chess.controller.web.GameController;
import chess.controller.web.HomeController;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");
        final HomeController homeController = new HomeController();
        final GameController gameController = new GameController();
    }
}
