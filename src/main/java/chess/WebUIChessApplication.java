package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.dto.request.MoveRequest;
import chess.domain.dto.response.Response;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
        final Gson GSON = new Gson();
        final JsonTransformer JSON_TRANSFORMER = new JsonTransformer();
        staticFiles.location("/public");
        port(8080);

        final ChessGame chessGame = new ChessGame();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            final String requests = req.body();
            final MoveRequest moveRequest = GSON.fromJson(requests, MoveRequest.class);

            try {
                chessGame.move(getPositionByCommands(moveRequest.source().split("")),
                    getPositionByCommands(moveRequest.target().split("")));

                if (chessGame.isKingDead()) {
                    chessGame.changeGameOver();
                }
                chessGame.nextTurn();
                return new Response("200", "성공");
            } catch (UnsupportedOperationException | IllegalArgumentException e) {
                return new Response("401", e.getMessage());
            }
        }, JSON_TRANSFORMER);

        get("/end", (req, res) -> {
            if (chessGame.isGameOver()) {
                return new Response("401", "게임 종료");
            }
            return new Response("200", "게임 진행중");
        }, JSON_TRANSFORMER);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static Position getPositionByCommands(final String[] commands) {
        return new Position(commands[0], commands[1]);
    }

}
