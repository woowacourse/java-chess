package chess.controller;

import chess.domain.board.Point;
import chess.service.HistoryService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

import java.sql.SQLDataException;
import java.util.HashMap;
import java.util.Map;

import static chess.controller.CommonController.nullable;

public class HistoryController {
    private HistoryController() {
        throw new AssertionError();
    }

    public static Map<String, Object> loadUnfinishedGame(Request request, Response response) throws SQLDataException {
        int round = Integer.parseInt(nullable(request.params(":round")));

        Map<String, Object> model = new HashMap<>();
        model.put("history", HistoryService.getInstance().selectLastHistory(round));
        return model;
    }

    public static Map<String, Object> insertHistory(Request request, Response response) throws SQLDataException {

        int round = Integer.parseInt(nullable(request.queryParams("round")));

        int prevX = Integer.parseInt(nullable(request.queryParams("prevX")));
        int prevY = Integer.parseInt(nullable(request.queryParams("prevY")));
        int nextX = Integer.parseInt(nullable(request.queryParams("nextX")));
        int nextY = Integer.parseInt(nullable(request.queryParams("nextY")));

        Point prev = Point.of(prevX, prevY);
        Point next = Point.of(nextX, nextY);

        Map<String, Object> model = new HashMap<>();
        model.put("history", HistoryService.getInstance().movePiece(round, prev, next));
        return model;
    }
}
