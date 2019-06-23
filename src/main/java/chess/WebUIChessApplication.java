package chess;

import chess.api.ChessGameController;
import chess.api.ExceptionController;
import chess.utils.JsonTransformer;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/assets");

        get("/", (req, res) -> ChessGameController.index());

        get("/game_play", (req, res) -> ChessGameController.playGet());

        get("/game_continue", (req, res) -> ChessGameController.continueGame());

        post("/game_play", (req, res) -> ChessGameController.playPost(req));

        post("/status", (req, res) -> ChessGameController.status(), new JsonTransformer());

        get("/result", (req, res) -> ChessGameController.result());

        get("/end", (req, res) -> ChessGameController.end());

        exception(RuntimeException.class, ExceptionController::exception);
    }
}
