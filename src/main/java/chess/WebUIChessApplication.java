package chess;

import chess.controller.CommonController;
import chess.controller.HistoryController;
import chess.controller.RoundInfoController;
import com.google.gson.Gson;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        setUp();

        Gson gson = new Gson();
        path("/api", () -> {
            path("/history", () -> {
                get("/", RoundInfoController::selectUnfinishedGameList, gson::toJson);
                get("/:round", HistoryController::loadUnfinishedGame, gson::toJson);
            });

            path("/game", () -> {
                post("/new", RoundInfoController::startGame, gson::toJson);
                post("/move", HistoryController::insertHistory, gson::toJson);
                get("/score/:round", RoundInfoController::getScore, gson::toJson);
                get("/winner/:round", RoundInfoController::getWinner, gson::toJson);
                get("/list", RoundInfoController::getFinishedGameList, gson::toJson);
            });
        });

        exception(IllegalArgumentException.class, CommonController::handlingException);
    }

    private static void setUp() {
        staticFileLocation("/templates");
    }
}
