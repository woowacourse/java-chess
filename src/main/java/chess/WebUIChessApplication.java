package chess;

import chess.controller.WebController;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/public");

        WebController webController = new WebController();

        get("/", (req, res) -> webController.game());

        get("/start", (req, res) -> webController.startGame());

        get("/resume", (req, res) -> webController.resumeGame());

        post("/move", (req, res) -> webController.move(req));
    }


}