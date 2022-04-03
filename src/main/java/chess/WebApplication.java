package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.board.Board;
import chess.web.controller.BoardController;

public class WebApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");
        BoardController boardController = new BoardController(new Board());

        get("/chess", boardController::printCurrentBoard);
        get("/chess/status", boardController::status);
        post("/chess/move", boardController::move);
        get("/chess/end", boardController::end);
    }
}
