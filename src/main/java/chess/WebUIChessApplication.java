package chess;

import chess.controller.WebController;
import chess.db.MySQLConnector;
import chess.service.ChessService;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        if (MySQLConnector.getConnection() != null) {
            System.out.println("DB 연결 완료");
        }

        WebController webController = new WebController(new ChessService());
        webController.play();
    }


}
