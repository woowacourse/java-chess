package chess;

import chess.controller.WebUIChessController;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    // 컨트롤러를 통해서 가져온다.


    public static void main(String[] args) {
        //Spark.port(3693);
        staticFiles.location("/static");

        WebUIChessController webUIChessController = new WebUIChessController();
        webUIChessController.run();

    }
}
