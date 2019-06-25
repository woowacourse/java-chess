package chess;

import chess.dao.RoundInfoDao;
import com.google.gson.Gson;

import java.util.Optional;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/templates");
        Gson gson = new Gson();
        path("/api", () -> {
            get("/history/:round", (request, response) -> {
                int round = Integer.parseInt(nullable(request.params(":round")));

                RoundInfoDao.getInstance().selectAllUnfinishedGame();

                return null;
            }, gson::toJson);

        });
    }

    private static String nullable(String param) {
        return Optional.ofNullable(param).orElseThrow(IllegalArgumentException::new);
    }
}
