package chess;

import chess.controller.WebController;
import chess.domains.board.Board;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/public");

        Board board = new Board();

        get("/", (req, res) -> WebController.game(board));

        get("/start", (req, res) -> WebController.startGame(board));

        get("/resume", (req, res) -> WebController.resumeGame(board));

        post("/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            return WebController.move(board, source, target);
        });
    }


}