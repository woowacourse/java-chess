package chess.controller;

import chess.service.dto.RoundInfoDto;
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

    private static final PlayerService PLAYER_SERVICE = PlayerService.getInstance();
    private static final RoundInfoService ROUND_INFO_SERVICE = RoundInfoService.getInstance();

    public static Map<String, Object> selectUnfinishedGameList(Request request, Response response) throws SQLDataException {
        Map<String, Object> model = new HashMap<>();
        model.put("gameList", ROUND_INFO_SERVICE.selectAllGame(false));
        return model;
    }

    public static Map<String, Object> getFinishedGameList(Request request, Response response) throws SQLDataException {
        Map<String, Object> model = new HashMap<>();
        model.put("gameList", ROUND_INFO_SERVICE.selectAllGame(true));
        return null;
    }

    public static Map<String, Object> startGame(Request request, Response response) throws SQLDataException {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(request.body());
        String whiteName = element.getAsJsonObject().get("whiteName").getAsString();
        String blackName = element.getAsJsonObject().get("blackName").getAsString();

        PLAYER_SERVICE.insertPlayer(whiteName, blackName);
        int round = ROUND_INFO_SERVICE.insertRoundInfo(whiteName, blackName);

        Map<String, Object> model = new HashMap<>();
        model.put("history", HistoryService.getInstance().insertFirstHistory(round));
        return model;
    }

    public static Map<String, Object> getScore(Request request, Response response) throws SQLDataException {
        int round = Integer.parseInt(nullable(request.params(":round")));

        Map<String, Object> model = new HashMap<>();
        model.put("result", ROUND_INFO_SERVICE.getScore(round));
        return model;
    }

    public static Map<String, Object> getWinner(Request request, Response response) throws SQLDataException {
        int round = Integer.parseInt(nullable(request.params(":round")));
        RoundInfoDto roundInfoDto = ROUND_INFO_SERVICE.selectRoundInfo(round);
        if (!roundInfoDto.isEnd()) {
            throw new IllegalArgumentException("종료된 게임이 아닙니다.");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("result", ROUND_INFO_SERVICE.selectGameResult(round));
        return model;
    }
}
