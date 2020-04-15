package chess;

import chess.controller.ChessWebController;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        final ChessWebController controller = new ChessWebController();

        staticFiles.location("/");

        get("/", (req, res) -> controller.gameId());

        post("/ready", (req, res) -> controller.canResume(req));

        post("/play", (req, res) -> controller.startNewGame(req));

        post("/resume", (req, res) -> controller.resumeGame(req));

        post("/move", (req, res) -> controller.move(req));
    }
}
