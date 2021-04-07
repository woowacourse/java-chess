package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.board.ChessBoard;
import chess.domain.service.WebController;


public class WebUIChessApplication {


    public static void main(String[] args) {
        staticFileLocation("public");
        WebController webController = new WebController();
        webController.setChessBoard(new ChessBoard());

        try {
            get("/", webController::main);
            post("/start", webController::start);
            post("/move", webController::move);
            post("/reset", webController::reset);
            post("/load", webController::load);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }


    }


}
