package chess;

import chess.controller.WebUIChessController;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        //Spark.port(3693);
        staticFiles.location("/static");

        WebUIChessController webUIChessController = new WebUIChessController();
        webUIChessController.run();

    }
}
