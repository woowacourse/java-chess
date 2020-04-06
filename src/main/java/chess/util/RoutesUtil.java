package chess.util;

import static spark.Spark.*;

import java.sql.SQLException;

import chess.dao.PlayerDao;
import spark.Request;
import spark.Response;

public class RoutesUtil {
    public static void addTemporaryPlayers() {
        // 플레이어 회원가입 / 로그인 구현 이전 foreign key 오류를 내지 않기 위해 임시로 DB에 플레이어 추가
        try {
            new PlayerDao().addInitialPlayers();
        } catch (SQLException ignored) {
        }
    }

    public static void configure() {
        port(8080);
        staticFiles.location("/public");
    }

    public static String handleCors(Request request, Response response) {
        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
        if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
        }
        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
        if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
        }
        return "OK";
    }

    public static void beforeEach(Request request, Response response) {
        response.header("Access-Control-Allow-Origin", "*");
        response.type("application/json");
    }
}
