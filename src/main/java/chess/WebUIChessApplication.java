package chess;

import chess.controller.ChessWebController;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        final ChessWebController controller = new ChessWebController();

        staticFiles.location("/");

        get("/", (req, res) -> controller.chessGame());

        post("/ready", (req, res) -> controller.enterGameRoom(req));

        post("/play", (req, res) -> controller.startGame(req));

        post("/resume", (req, res) -> controller.resumeGame(req));

        post("/move", (req, res) -> controller.move(req));
    }
}
