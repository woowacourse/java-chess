package chess.controller;

import chess.dto.RoundInfoDto;
import chess.service.HistoryService;
import chess.service.PlayerService;
import chess.service.RoundInfoService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

import java.sql.SQLDataException;
import java.util.HashMap;
import java.util.Map;

import static chess.controller.CommonController.nullable;

public class RoundInfoController {

    public static Map<String, Object> selectUnfinishedGameList(Request request, Response response) throws SQLDataException {
        Map<String, Object> model = new HashMap<>();
        model.put("gameList", RoundInfoService.getInstance().selectAllGame(false));
        return model;
    }

    public static Map<String, Object> getFinishedGameList(Request request, Response response) throws SQLDataException {
        Map<String, Object> model = new HashMap<>();
        model.put("gameList", RoundInfoService.getInstance().selectAllGame(true));
        return null;
    }

    public static Map<String, Object> startGame(Request request, Response response) throws SQLDataException {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(request.body());
        String whiteName = element.getAsJsonObject().get("whiteName").getAsString();
        String blackName = element.getAsJsonObject().get("blackName").getAsString();

        int playerResult = PlayerService.getInstance().insertPlayer(whiteName, blackName);
        int round = RoundInfoService.getInstance().insertRoundInfo(whiteName, blackName);

        Map<String, Object> model = new HashMap<>();
        model.put("history", HistoryService.getInstance().insertFirstHistory(round));
        return model;
    }

    public static Map<String, Object> getScore(Request request, Response response) throws SQLDataException {
        int round = Integer.parseInt(nullable(request.params(":round")));

        Map<String, Object> model = new HashMap<>();
        model.put("result", RoundInfoService.getInstance().getScore(round));
        return model;
    }

    public static Map<String, Object> getWinner(Request request, Response response) throws SQLDataException {
        int round = Integer.parseInt(nullable(request.params(":round")));
        RoundInfoDto roundInfoDto = RoundInfoService.getInstance().selectRoundInfo(round);
        if (!roundInfoDto.isEnd()) {
            throw new IllegalArgumentException("아직 끝나지 않은 게임 처리");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("result", RoundInfoService.getInstance().selectGameResult(round));
        return model;
    }
}
