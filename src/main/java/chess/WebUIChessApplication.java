package chess;

import chess.api.ChessGameAPI;
import chess.api.ExceptionAPI;
import chess.view.JsonTransformer;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/assets");

        get("/", ChessGameAPI::index);

        get("/game_play", ChessGameAPI::gamePlayGet);

        get("/game_continue", ChessGameAPI::continueGame);

        post("/game_play", ChessGameAPI::gamePlayPost);

        post("/status", ChessGameAPI::status, new JsonTransformer());

        get("/result", ChessGameAPI::result);

        get("/end", ChessGameAPI::end);

        exception(RuntimeException.class, ExceptionAPI::exception);
    }
}
