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

        get("/", ChessGameController::index);

        get("/game_play", ChessGameController::playGet);

        get("/game_continue", ChessGameController::continueGame);

        post("/game_play", ChessGameController::playPost);

        post("/status", ChessGameController::status, new JsonTransformer());

        get("/result", ChessGameController::result);

        get("/end", ChessGameController::end);

        exception(RuntimeException.class, ExceptionController::exception);
    }
}
