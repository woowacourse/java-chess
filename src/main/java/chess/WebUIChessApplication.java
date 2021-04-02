package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.board.ChessBoard;
import chess.domain.service.WebController;
import com.google.gson.Gson;

public class WebUIChessApplication {


    public static void main(String[] args) {
        staticFileLocation("public");
        Gson gson = new Gson();
        WebController webController = new WebController(gson);
        webController.setChessBoard(new ChessBoard());

        try {
            get("/", WebController::main);
            post("/start", WebController::start);
            post("/move", WebController::move);
            post("/reset", WebController::reset);
//            post("/save", WebController::save);
            post("/load", WebController::load);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }


    }


}
