package chess;

import chess.controller.HistoryController;
import chess.dao.RoundInfoDao;
import chess.dto.RoundInfoDto;
import com.google.gson.Gson;

import java.util.List;
import java.util.Optional;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/templates");
        Gson gson = new Gson();
        path("/api", () -> {
            path("/history", () -> {
                get("/", HistoryController::selectAllUnfinishedGame, gson::toJson);
                get("/:round", HistoryController::selectUnfinishedGame, gson::toJson);
            });

        });
    }

    public static String nullable(String param) {
        return Optional.ofNullable(param).orElseThrow(IllegalArgumentException::new);
    }
}
