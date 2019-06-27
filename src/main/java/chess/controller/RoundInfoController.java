package chess.controller;

import chess.service.HistoryService;
import chess.service.PlayerService;
import chess.service.RoundInfoService;
import chess.service.dto.RoundInfoDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

import java.sql.SQLDataException;
import java.util.HashMap;
import java.util.Map;

import static chess.controller.common.CommonController.nullable;
import static chess.controller.common.ModelKey.*;

public class RoundInfoController {

    private static final PlayerService PLAYER_SERVICE = PlayerService.getInstance();
    private static final RoundInfoService ROUND_INFO_SERVICE = RoundInfoService.getInstance();

    private static final String WHITE_NAME = "whiteName";
    private static final String BLACK_NAME = "blackName";
    private static final String ROUND_PARAM = ":round";


    public static Map<String, Object> selectUnfinishedGameList(Request request, Response response) throws SQLDataException {
        Map<String, Object> model = new HashMap<>();
        model.put(GAME_LIST, ROUND_INFO_SERVICE.selectAllGame(false));
        return model;
    }

    public static Map<String, Object> getFinishedGameList(Request request, Response response) throws SQLDataException {
        Map<String, Object> model = new HashMap<>();
        model.put(GAME_LIST, ROUND_INFO_SERVICE.selectAllGame(true));
        return null;
    }

    public static Map<String, Object> startGame(Request request, Response response) throws SQLDataException {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(request.body());
        String whiteName = element.getAsJsonObject().get(WHITE_NAME).getAsString();
        String blackName = element.getAsJsonObject().get(BLACK_NAME).getAsString();

        PLAYER_SERVICE.insertPlayer(whiteName, blackName);
        int round = ROUND_INFO_SERVICE.insertRoundInfo(whiteName, blackName);

        Map<String, Object> model = new HashMap<>();
        model.put(HISTORY, HistoryService.getInstance().insertFirstHistory(round));
        return model;
    }

    public static Map<String, Object> getScore(Request request, Response response) throws SQLDataException {
        int round = Integer.parseInt(nullable(request.params(ROUND_PARAM)));

        Map<String, Object> model = new HashMap<>();
        model.put(RESULT, ROUND_INFO_SERVICE.getScore(round));
        return model;
    }

    public static Map<String, Object> getWinner(Request request, Response response) throws SQLDataException {
        int round = Integer.parseInt(nullable(request.params(ROUND_PARAM)));
        RoundInfoDto roundInfoDto = ROUND_INFO_SERVICE.selectRoundInfo(round);
        if (!roundInfoDto.isEnd()) {
            throw new IllegalArgumentException("종료된 게임이 아닙니다.");
        }

        Map<String, Object> model = new HashMap<>();
        model.put(RESULT, ROUND_INFO_SERVICE.selectGameResult(round));
        return model;
    }
}
